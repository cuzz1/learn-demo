package com.cuzz.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * @Author: cuzz
 * @Date: 2018/10/16 13:51
 * @Description:
 */
public class Response {

    private ChannelHandlerContext context;
    private HttpRequest request;

    public Response(ChannelHandlerContext context, HttpRequest request) {
        this.context = context;
        this.request = request;
    }

    public void write(String out) throws Exception{
        if (out == null) return;
        try {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "text/json");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(EXPIRES, 0);
            if (HttpHeaders.isKeepAlive(request)) {
                response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            context.write(response);
        } finally {
            context.flush();
        }

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
