package com.cuzz.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @Author: cuzz
 * @Date: 2018/10/17 10:41
 * @Description:
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    // classpath
    private URL baseURL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();

    private final String WEB_ROOT = "webroot";

    @Override
    protected void channelRead0(ChannelHandlerContext context, FullHttpRequest request) throws Exception {
        // 获取到客户端请求的uri
        String uri = request.getUri();
        String page = uri.equals("/") ? "chat.html" : uri;
        RandomAccessFile file = new RandomAccessFile(getFileFromRoot(page), "r");

        // 把文件写出去
        HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);

        String contextType = "text/html;";
        if(uri.endsWith(".css")){
            contextType = "text/css;";
        }else if(uri.endsWith(".js")){
            contextType = "text/javascript;";
        }else if(uri.toLowerCase().matches("(jpg|png|gif)$")){
            String ext = uri.substring(uri.lastIndexOf("."));
            contextType = "image/" + ext;
        }
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, contextType + "charset=utf-8;");

        boolean keepAlive = HttpHeaders.isKeepAlive(request);

        if (keepAlive) {
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        context.write(response);

        context.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));

        ChannelFuture future = context.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

        file.close();
    }

    private File getFileFromRoot(String fileName) throws Exception {
        String path = baseURL.toURI() + WEB_ROOT + "/" + fileName;
        path = path.contains("file:") ? path.substring(5) : path;
        path = path.replaceAll("//", "/");
        return new File(path);
    }
}
