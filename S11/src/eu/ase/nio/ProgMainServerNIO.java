package eu.ase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ProgMainServerNIO {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();

            InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 8989);
            serverSocket.bind(serverAddr);

            serverSocket.configureBlocking(false);

            int ops = serverSocket.validOps();
            serverSocket.register(selector, ops, null);
            while (true) {
                System.out.println("Waiting for connections...");
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> kIterator = keys.iterator();

                while (kIterator.hasNext()) {
                    SelectionKey myKey = kIterator.next();

                    if(myKey.isAcceptable()) {
                        SocketChannel sClient = serverSocket.accept();
                        sClient.configureBlocking(false);
                        sClient.register(selector, SelectionKey.OP_READ);
                        System.out.println("Connection accepted: " + sClient.getLocalAddress());
                    } else if (myKey.isReadable()) {
                        SocketChannel sClient = (SocketChannel) myKey.channel();
                        ByteBuffer cBuffer = ByteBuffer.allocate(256);
                        sClient.read(cBuffer);
                        String result = new String(cBuffer.array()).trim();

                        if(result.equals("Google")) {
                            sClient.close();
                            System.out.println("Close connection - we got last company name - Google");
                        }
                        kIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
