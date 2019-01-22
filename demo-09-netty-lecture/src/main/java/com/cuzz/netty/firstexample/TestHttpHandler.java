package com.cuzz.netty.firstexample;

import com.sun.org.apache.xpath.internal.SourceTree;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @Author: cuzz
 * @Date: 2018/12/31 18:35
 * @Description:
 */
public class TestHttpHandler extends SimpleChannelInboundHandler<HttpObject>{
    // 读取客户端请求并响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("请求方法名：" + httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("访问/favicon.ico被忽略...");
                return;
            }
            ByteBuf context = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, context);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, context.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
