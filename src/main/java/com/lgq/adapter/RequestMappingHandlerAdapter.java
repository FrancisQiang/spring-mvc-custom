package com.lgq.adapter;

import com.lgq.handler.HandlerMethod;
import com.lgq.ioc.BeanFactory;
import com.lgq.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ModelAndView modelAndView = null;
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method != null) {
            modelAndView = (ModelAndView)method.invoke(BeanFactory.getBean(((HandlerMethod) handler).getBeanType()));
        }
        return modelAndView;
    }

    @Override
    public boolean support(Object handler) {
        return handler instanceof HandlerMethod;
    }
}
