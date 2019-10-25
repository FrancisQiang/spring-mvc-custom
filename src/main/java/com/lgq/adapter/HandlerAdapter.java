package com.lgq.adapter;

import com.lgq.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lgq
 * @date 2019/10/25
 */
public interface HandlerAdapter {

    /**
     * 处理请求返回视图信息
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return 视图信息
     * @throws Exception 异常
     */
    ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception;

    /**
     * 是否支持该处理器
     * @param handler 处理器
     * @return 是否支持
     */
    boolean support(Object handler);

}
