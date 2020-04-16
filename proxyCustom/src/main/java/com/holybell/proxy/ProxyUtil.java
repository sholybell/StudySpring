package com.holybell.proxy;

import com.holybell.dao.UserDao;
import com.holybell.dao.UserDaoImpl;

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
 * 模拟动态代理的实现原理，这个例子里面代理方法是写死的61行的日志输出
 */
public class ProxyUtil {

    /**
     */
    public static Object newInstance(Object target) {
        Object proxy = null;
        Class targetInf = target.getClass().getInterfaces()[0];
        Method methods[] = targetInf.getDeclaredMethods();
        String line = "\n";
        String tab = "\t";
        String infName = targetInf.getSimpleName();
        String content = "";
        String packageContent = "package com.google;" + line;
        String importContent = "import " + targetInf.getName() + ";" + line;
        String clazzFirstLineContent = "public class $Proxy implements " + infName + "{" + line;
        String filedContent = tab + "private " + infName + " target;" + line;
        String constructorContent = tab + "public $Proxy (" + infName + " target){" + line
                + tab + tab + "this.target =target;"
                + line + tab + "}" + line;
        String methodContent = "";
        for (Method method : methods) {
            String returnTypeName = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            // Sting.class String.class
            Class args[] = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            int flag = 0;
            for (Class arg : args) {
                String temp = arg.getSimpleName();
                //String
                //String p0,Sting p1,
                argsContent += temp + " p" + flag + ",";
                paramsContent += "p" + flag + ",";
                flag++;
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }

            methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ") {" + line
                    + tab + tab + "System.out.println(\"log\");" + line;
            // 校验是否有返回值
            if (returnTypeName.equals("void")) {
                methodContent += tab + tab + "target." + methodName + "(" + paramsContent + ");" + line + tab + "}" + line;
            } else {
                methodContent += tab + tab + "return target." + methodName + "(" + paramsContent + ");" + line + tab + "}" + line;
            }

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

            // 调用JDK的编译器编译java类
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            // 从磁盘上面加载class文件
            URL[] urls = new URL[]{new URL("file:D:\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.google.$Proxy");

            Constructor constructor = clazz.getConstructor(targetInf);

            proxy = constructor.newInstance(target);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return proxy;
    }

    public static void deleteAllFilesOfDir(File path) {
        if (null != path) {
            if (!path.exists())
                return;
            if (path.isFile()) {
                boolean result = path.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc(); // 回收资源
                    result = path.delete();
                }
            }
            File[] files = path.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
            }
            path.delete();
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        UserDao proxy = (UserDao) newInstance(userDao);
        proxy.query("key");
        // 可以改成递归删除
        File file = new File("d:\\com\\google\\$Proxy.java");
        File clsFile = new File("d:\\com\\google\\$Proxy.class");
        File directory = new File("d:\\com\\google\\");
        File d = new File("d:\\com\\");
        deleteAllFilesOfDir(file);
        deleteAllFilesOfDir(clsFile);
        deleteAllFilesOfDir(directory);
        deleteAllFilesOfDir(d);
    }
}
