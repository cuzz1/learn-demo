package com.cuzz.servlets;

import com.cuzz.http.Request;
import com.cuzz.http.Response;
import com.cuzz.http.Servlet;

/**
 * @Author: cuzz
 * @Date: 2018/10/16 13:52
 * @Description:
 */
public class MyServlet extends Servlet{
    @Override
    public void doGet(Request request, Response response) throws Exception {
        response.write(request.getParameter("name"));
    }

    @Override
    public void doPost(Request request, Response response) throws Exception {
        doGet(request, response);
    }
}
