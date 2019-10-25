package com.lgq.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lgq
 * @date 2019/10/25
 */
public interface SimpleHandler {

    /**
     * 处理请求
     * @param request 请求
     * @param response 响应
     * @throws Exception 异常
     */
    void handleRequest(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
