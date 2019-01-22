package com.cuzz.nio;

import sun.misc.Cleaner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: cuzz
 * @Date: 2019/1/8 17:10
 * @Description:
 */
public class NioClient {
    public static void main(String[] args){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (SelectionKey selectionKey : selectionKeys ) {
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()) {
                            client.finishConnect();
                            System.out.println(client);
                            ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                            writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.submit(() -> {
                                while (true) {
                                    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                    BufferedReader bf = new BufferedReader(inputStreamReader);
                                    String message = bf.readLine();
                                    ByteBuffer buffer = ByteBuffer.allocate(512);
                                    buffer.put(message.getBytes());
                                    buffer.flip();
                                    client.write(buffer);
                                }
                            });
                        }
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = client.read(byteBuffer);
                        if (read > 0) {
                            String message = new String(byteBuffer.array());
                            System.out.println(message);
                        }

                    }
                }
                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
