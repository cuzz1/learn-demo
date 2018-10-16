package com.cuzz.http;

/**
 * @Author: cuzz
 * @Date: 2018/10/16 13:48
 * @Description:
 */
public abstract class Servlet {
    public abstract void doGet(Request request, Response response) throws Exception;
    public abstract void doPost(Request request, Response response) throws Exception;

}
