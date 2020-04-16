package com.holybell.proxy;

import com.holybell.util.CustomInvocationHandler;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 针对{@link ProxyUtil}没法实现灵活的替换增强逻辑的问题，做出改进
 * 模拟JDK使用InvocationHandler实现动态代理的原理：
 * 1. 使用{@link CustomInvocationHandler}来封装增强的逻辑
 * 2. 通过接口获取到要实现的方法，在代理类中生成相应的方法
 * 3. 通过反射获取到要被代理的方法method对象
 * 4. 通过目标对象（被代理对象）和method对象以及入参进行反射调用目标方法
 * <p>
 * public class $Proxy implements TestDao{
 *     private CustomInvocationHandler h;  // 生成的代理对象持有 CustomInvocationHandler 成员变量，这个变量会封装目标对象和要增强的逻辑
 *     public $Proxy (CustomInvocationHandler h){
 *         this.h = h;
 *     }
 *     public String query(String p)throws Exception {
 *         Class[] clazzs = new Class[]{String.class};  // 通过反射获取到当前代理对象调用的方法的目标对象的方法对象
 *         Method method = Class.forName("com.holybell.dao.TestDao").getDeclaredMethod("query",clazzs);
 *         Object[] args = new Object[]{p};     // 通过被调用的代理对象入参收集到要传递给目标对象的目标方法的实参
 *         return (String)h.invoke(method,args);    // 通过CustomInvocationHandler对象进行调用
 *     }
 * }
 *
 * ------------------------------------------------------------------------------
 *
 * public class TestCustomInvocationHandler implements CustomInvocationHandler {  // 实现CustomInvocationHandler，并且通过构造函数封装目标对象
 *     Object target;
 *
 *     public TestCustomInvocationHandler(Object target) {
 *         this.target = target;
 *     }
 *
 *     @Override
 *     public Object invoke(Method method, Object[] args) {
 *         try {
 *             System.out.println("--------------------------------");  // 这里就是要增强的逻辑
 *             return method.invoke(target, args);          // 执行完增强的逻辑之后，反射调用目标对象的方法，method和args都是由代理对象传递过来的
 *         } catch (IllegalAccessException e) {
 *             e.printStackTrace();
 *         } catch (InvocationTargetException e) {
 *             e.printStackTrace();
 *         }
 *         return null;
 *     }
 * }
 *
 */
public class ProxyUtil2 {

    /**
     */
    public static Object newInstance(Class targetInf, CustomInvocationHandler h) {
        Object proxy = null;
        Method methods[] = targetInf.getDeclaredMethods();
        String line = "\n";
        String tab = "\t";
        String infName = targetInf.getSimpleName();
        String content = "";
        String packageContent = "package com.google;" + line;
        String importContent = "import " + targetInf.getName() + ";" + line
                + "import com.holybell.util.CustomInvocationHandler;" + line
                + "import java.lang.Exception;" + line
                + "import java.lang.reflect.Method;" + line;
        String clazzFirstLineContent = "public class $Proxy implements " + infName + "{" + line;
        String filedContent = tab + "private CustomInvocationHandler h;" + line;
        String constructorContent = tab + "public $Proxy (CustomInvocationHandler h){" + line
                + tab + tab + "this.h = h;"
                + line + tab + "}" + line;
        String methodContent = "";
        for (Method method : methods) {
            String returnTypeName = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            // Sting.class String.class
            Class args[] = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            String argsClassContent = "";
            int flag = 0;
            for (Class arg : args) {
                String temp = arg.getSimpleName();
                //String
                //String p0,Sting p1,
                argsContent += temp + " p" + flag + ",";
                argsClassContent += temp + ".class" + ",";
                paramsContent += "p" + flag + ",";
                flag++;
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                argsClassContent = argsClassContent.substring(0, argsClassContent.lastIndexOf(","));
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }

            methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ")throws Exception {" + line
                    + tab + tab + "Class[] clazzs = new Class[]{" + argsClassContent + "};" + line
                    + tab + tab + "Method method = Class.forName(\"" + targetInf.getName() + "\").getDeclaredMethod(\"" + methodName + "\",clazzs);" + line
                    + tab + tab + "Object[] args = new Object[]{" + paramsContent + "};" + line
                    + tab + tab + "return (" + returnTypeName + ")h.invoke(method,args);" + line;
            methodContent += tab + "}" + line;
        }

        content = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";

        File dirctory = new File("d:\\com\\google");
        File file = new File("d:\\com\\google\\$Proxy.java");
        try {
            dirctory.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            fw.close();


            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.google.$Proxy");

            Constructor constructor = clazz.getConstructor(CustomInvocationHandler.class);
            proxy = constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxy;
    }
}
