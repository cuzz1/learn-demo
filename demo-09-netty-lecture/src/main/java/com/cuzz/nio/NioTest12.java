package com.cuzz.nio;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: cuzz
 * @Date: 2019/1/6 18:14
 * @Description:
 */
public class NioTest12 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];
        for (int i = 0, port = 5000; i < ports.length; i++) {
            ports[i] = port++;
        }

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听的端口：" + ports[i]);
        }

        while (true) {
            int num = selector.select();
            System.out.println("num: " + num);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys: " + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // 获取对应的channel
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 把他注册到selector上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获取客户端连接: " + socketChannel);
                } else if (selectionKey.isReadable()) {
                   SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read <= 0) {
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                    }
                    iterator.remove();
                }
            }
        }

    }
}
