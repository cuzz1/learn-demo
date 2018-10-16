package com.cuzz.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @Author: cuzz
 * @Date: 2018/10/16 13:17
 * @Description:
 */
public class Tomcat {

    private int port = 8080;

    public Tomcat(int port) {
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
                        // 业务逻辑链路

                        // 编码器
                        client.pipeline().addLast(new HttpResponseEncoder());
                        // 解码器
                        client.pipeline().addLast(new HttpRequestDecoder());
                        // 业务逻辑层
                        client.pipeline().addLast(new TomcatHandler());

                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // 主线程的配置
                .childOption(ChannelOption.SO_KEEPALIVE, true); // 子线程的配置

        ChannelFuture future = server.bind(port).sync();
        System.out.println("### Tomcat已经启动 port: " + port);
        future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Tomcat tomcat = new Tomcat(8080);
        tomcat.start();
    }
}
