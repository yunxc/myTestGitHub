package com.yun.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * @date 2021/6/7 20:48
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        // 本地通道
        FileInputStream fileInputStream = new FileInputStream("");
        FileChannel channel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("");
        FileChannel channel2 = fileOutputStream.getChannel();

        // 网络通道
        Socket socket = new Socket();
        SocketChannel channel3 = socket.getChannel();

        ServerSocket serverSocket = new ServerSocket();
        ServerSocketChannel channel4 = serverSocket.getChannel();

        DatagramSocket datagramSocket = new DatagramSocket();
        DatagramChannel channel5 = datagramSocket.getChannel();

        // 最后要关闭通道

    }
}
