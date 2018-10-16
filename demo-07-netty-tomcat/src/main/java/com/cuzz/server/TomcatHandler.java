package com.cuzz.server;

import com.cuzz.http.Request;
import com.cuzz.http.Response;
import com.cuzz.servlets.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @Author: cuzz
 * @Date: 2018/10/16 13:37
 * @Description:
 */
public class TomcatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest r= (HttpRequest) msg;

            Request request = new Request(ctx, r);
            Response response = new Response(ctx, r);

            new MyServlet().doGet(request, response);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
