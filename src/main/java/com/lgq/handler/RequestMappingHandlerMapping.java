package com.lgq.handler;

import com.lgq.annotation.Controller;
import com.lgq.annotation.RequestMapping;
import com.lgq.ioc.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

    private Map<String, HandlerMethod> urlMap = new HashMap<>();

    public void init() {
        // 首先从IOC容器中获取所有对象的beanNames
        List<String> beanNames = BeanFactory.getBeanNamesOfType(Object.class);
        // 遍历beanNames通过beanName获取相应的clazz
        if (beanNames != null) {
            for (String beanName: beanNames) {
                Class<?> clazz = BeanFactory.getType(beanName);
                // 判断clazz对象是否是处理器，如果是则处理
                if (clazz != null && isHandler(clazz)) {
                    // 获取该类所有方法
                    Method[] methods = clazz.getDeclaredMethods();
                    // 遍历所有方法并判断存在注解RequestMapping的方法
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            // 获取该方法的注解并获取该注解的名称value 后面作为map的key
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String value = requestMapping.value();
                            // 通过beanName, clazz， method创将handlerMethod
                            HandlerMethod handlerMethod = new HandlerMethod(beanName, clazz, method);
                            // 存入映射
                            urlMap.put(value, handlerMethod);
                        }
                    }
                }
            }
        }

    }

    /**
     * 判断类上的注解是否存在 Controller 或者 RequestMapping
     * @param clazz 类
     * @return 是否存在控制器对应的注解
     */
    private boolean isHandler(Class<?> clazz) {
        return clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(RequestMapping.class);
    }

    @Override
    public Object getHandler(HttpServletRequest request) throws Exception {
        // 通过初始化方法调用的init生成的urlMap中寻找对应的处理器
        return urlMap.get(request.getRequestURI());
    }
}

