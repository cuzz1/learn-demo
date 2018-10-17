package com.cuzz.server;

import com.cuzz.protocol.IMDecoder;
import com.cuzz.protocol.IMEncoder;
import com.cuzz.server.handler.HttpHandler;
import com.cuzz.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.logging.SocketHandler;


/**
 * @Author: cuzz
 * @Date: 2018/10/17 10:31
 * @Description:
 */
public class ChatServer {

    private int port = 80;


    public ChatServer() {

    }
    public ChatServer(int port) {
        this.port = port;
    }



    public void start() throws InterruptedException {
        // Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // Netty服务
        ServerBootstrap server = new ServerBootstrap();
        try {

            server.group(bossGroup, workGroup)
                    // 主线程的处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程的处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {

                            ChannelPipeline pipeline = client.pipeline();


                            pipeline.addLast(new IMDecoder());
                            pipeline.addLast(new IMEncoder());


                            //================HTTP请求==============
                            // 解码和编码HTTP请求
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            // 主要用于处理大数据流,比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的 ,加上这个handler我们就不用考虑这个问题了
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpHandler());


                            // ===============WebSocket协议====================
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                            pipeline.addLast(new WebSocketHandler());



                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // 主线程的配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // 子线程的配置

            ChannelFuture future = server.bind(port).sync();
            System.out.println("### ChatServer已经启动 port: " + port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
