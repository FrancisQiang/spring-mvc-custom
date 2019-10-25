package com.lgq.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class AddBookHandler implements SimpleHandler{

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("添加书本成功！");
        writer.close();
    }
}
