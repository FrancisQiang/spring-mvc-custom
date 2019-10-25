package com.lgq.adapter;

import com.lgq.handler.SimpleHandler;
import com.lgq.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class SimpleHandlerAdapter implements HandlerAdapter {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ((SimpleHandler)handler).handleRequest(request, response);
        return null;
    }

    @Override
    public boolean support(Object handler) {
        return handler instanceof SimpleHandler;
    }
}
