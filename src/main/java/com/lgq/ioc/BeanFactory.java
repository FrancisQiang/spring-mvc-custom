package com.lgq.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class BeanFactory {

    private static ClassPathXmlApplicationContext context;

    /**
     * 初始化容器
     *
     * @param contextConfigLocation 配置文件路径
     */
    public static void initBeanFactory(String contextConfigLocation) {
        context = new ClassPathXmlApplicationContext(contextConfigLocation);
    }

    public static <T> List<T> getBeansOfType(Class<?> clazz) {
        ArrayList<T> result = new ArrayList<>();
        Map<String, ?> beansOfType = context.getBeansOfType(clazz);
        for (Object object : beansOfType.values()) {
            result.add((T) object);
        }
        return result;
    }

    public static List<String> getBeanNamesOfType(Class<Object> objectClass) {
        String[] beanNamesForType = context.getBeanNamesForType(objectClass);
        if (beanNamesForType == null) {
            return null;
        } else {
            return new ArrayList<>(Arrays.asList(beanNamesForType));
        }
    }

    public static Class<?> getType(String beanName) {
        return context.getType(beanName);
    }

    public static Object getBean(Class<?> clazz) {
        return context.getBean(clazz);
    }

}
