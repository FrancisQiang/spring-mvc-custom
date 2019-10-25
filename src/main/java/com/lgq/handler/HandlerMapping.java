package com.lgq.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lgq
 * @date 2019/10/25
 */
public interface HandlerMapping {

    /**
     * 获取处理器
     * @param request 请求
     * @return 处理器
     * @throws Exception 异常
     */
    Object getHandler(HttpServletRequest request) throws Exception;

}
