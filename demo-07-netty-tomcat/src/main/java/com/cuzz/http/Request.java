package com.cuzz.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @Author: cuzz
 * @Date: 2018/10/16 13:50
 * @Description:
 */
public class Request {

    private ChannelHandlerContext context;
    private HttpRequest request;

    public Request(ChannelHandlerContext context, HttpRequest request) {
        this.context = context;
        this.request = request;
    }

    public String getUri() {
        return request.getUri();
    }

    public String getMethod() {
        return request.getMethod().name();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
        return decoder.parameters();
    }
}
