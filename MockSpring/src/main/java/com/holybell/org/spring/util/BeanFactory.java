package com.holybell.org.spring.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    /**
     * 集中存放扫描配置文件，反射生成的Bean对象
     * key ： beanName value : 对象
     */
    Map<String, Object> map = new ConcurrentHashMap<>();

    public BeanFactory(String xml) {
        parseXml(xml);
    }

    public void parseXml(String xml) throws MySpringException {

        File file = new File(this.getClass().getResource("/").getPath() + "//" + xml);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element elementRoot = document.getRootElement();
            Attribute attribute = elementRoot.attribute("default");
            boolean flag = false;
            if (attribute != null) {
                flag = true;
            }
            for (Iterator<Element> itFirlst = elementRoot.elementIterator(); itFirlst.hasNext(); ) {
                // 1. 扫描所有的一级子标签，即bean标签，解析id和class属性，反射生成对象
                Element elementFirstChil = itFirlst.next();
                Attribute attributeId = elementFirstChil.attribute("id");
                String beanName = attributeId.getValue();
                Attribute attributeClass = elementFirstChil.attribute("class");
                String clazzName = attributeClass.getValue();
                Class clazz = Class.forName(clazzName);

                // 2. 维护依赖关系，看这个对象有没有依赖（判断是否有property,或者判断类是否有属性），如果有则注入
                Object object = null;
                for (Iterator<Element> itSecond = elementFirstChil.elementIterator(); itSecond.hasNext(); ) {
                    // 得到ref的value，通过value得到对象（map）
                    // 得到name的值，然后根据值获取一个Filed的对象
                    // 通过field的set方法set那个对象

                    //<property name="dao" ref="dao"></property>
                    Element elementSecondChil = itSecond.next();
                    if (elementSecondChil.getName().equals("property")) {
                        //由於是setter，沒有特殊的构造方法
                        object = clazz.newInstance();
                        String refVlaue = elementSecondChil.attribute("ref").getValue();
                        Object injetObject = map.get(refVlaue);
                        String nameVlaue = elementSecondChil.attribute("name").getValue();
                        Field field = clazz.getDeclaredField(nameVlaue);
                        field.setAccessible(true);
                        field.set(object, injetObject);

                    } else {
                        //证明有特殊构造方法
                        String refVlaue = elementSecondChil.attribute("ref").getValue();
                        Object injetObject = map.get(refVlaue);
                        Class injectObjectClazz = injetObject.getClass();
                        Constructor constructor = clazz.getConstructor(injectObjectClazz.getInterfaces()[0]);
                        object = constructor.newInstance(injetObject);
                    }

                }
                if (object == null) {
                    if (flag) {
                        if (attribute.getValue().equals("byType")) {
                            //判断是否存在依赖
                            Field fields[] = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                //得到属性类型，比如String aa那么这里的field.getType()=String.class
                                Class injectObjectClazz = field.getType();
                                /**
                                 * 由於是bytype 所以需要遍历map当中的所有对象
                                 * 判断对象的类型是不是和这个injectObjectClazz相同
                                 */
                                int count = 0;
                                Object injectObject = null;
                                for (String key : map.keySet()) {
                                    Class temp = map.get(key).getClass().getInterfaces()[0];
                                    if (temp.getName().equals(injectObjectClazz.getName())) {
                                        injectObject = map.get(key);
                                        //记录找到一个，因为可能找到多个count
                                        count++;
                                    }
                                }

                                if (count > 1) {
                                    throw new MySpringException("需要一个对象，但是找到了两个对象");
                                } else {
                                    object = clazz.newInstance();
                                    field.setAccessible(true);
                                    field.set(object, injectObject);
                                }
                            }
                        }
                    }
                }

                if (object == null) {//沒有子标签
                    object = clazz.newInstance();
                }
                map.put(beanName, object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(map);
    }

    public Object getBean(String beanName) {
        return map.get(beanName);
    }
}
