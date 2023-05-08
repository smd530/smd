package com.example.smd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * NIO
 * @author shanmingda
 */
public class NIODemo {

    public static void main(String[] args) throws IOException {
        // 1.创建selector用来监听多路IO消息 （文件描述符 fd）
        // Selector 担任了重要的通知角色 可以将任意IO注册到selector 通过非阻塞轮询selector来得知哪些路IO有消息了
        // 底层是epoll （linux下）
        // 后续会把server端注册上来 有服务端被客户端连接上来的IO消息
        // 也会把每个客户端连接注册上来 有客户端发送过来的数据
        Selector selector = Selector.open();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 2. 把server端注册上去
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 1111));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        boolean flag = false;
        while (true) {
            // 3. select方法是NIO的体现 他是非阻塞的 函数会立马返回
            if (selector.select() == 0) {
                continue;
            }
            
            // 4. 如果有至少一路IO有消息 那么set不为空
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                if (key.isAcceptable()) {
                    // 因为我们只注册了serverSocketChannel这一个可以accept的所以这里强转即可
//                    SocketChannel socketChannel = new SocketChannel() {
//                    };
//                    socketChannel.configureBlocking(false);
//                    System.out.println("客户端连接" + socketChannel.getRemoteAddress());
//                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {

                }
            }

        }

    }
}
