package com.cuzz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author: cuzz
 * @Date: 2019/1/7 15:39
 * @Description:
 */
public class NioServer {
    // 储存客户端连接
    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8898));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    try {
                        if (selectionKey.isAcceptable()) {  // 可以读
                            read(selector, selectionKey);
                        } else if (selectionKey.isReadable()) { // 可以写
                            write(selector, selectionKey);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                selectionKeys.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void write(Selector selector, SelectionKey selectionKey) throws IOException{
        SocketChannel client = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        int read = client.read(byteBuffer);
        if (read > 0) {
            byteBuffer.flip();
            Charset charset = Charset.forName("utf-8");
            String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
            System.out.println(client + ": " + receiveMessage);

            String key = null;
            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                if (entry.getValue() == client) {
                    key = entry.getKey();
                    break;
                }
            }
            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                SocketChannel value = entry.getValue();
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.put((key + " :" + receiveMessage).getBytes());
                writeBuffer.flip();
                value.write(writeBuffer);
            }
        }
    }

    private static void read(Selector selector, SelectionKey selectionKey) throws IOException{
        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
        System.out.println(server);
        SocketChannel client = server.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        String key = UUID.randomUUID().toString();
        // 保存客户端
        clientMap.put(key, client);
    }
}
