package com.lgq.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class SimpleHandlerMapping implements HandlerMapping {

    @Override
    public Object getHandler(HttpServletRequest request) throws Exception {
        // 获取请求的uri
        String uri = request.getRequestURI();

        // 根据映射规则获取对应的handler
        if ("/addBook".equals(uri)) {
            return new AddBookHandler();
        } else if ("/deleteBook".equals(uri)) {
            return new DeleteBookHandler();
        }
        return null;
    }

}
