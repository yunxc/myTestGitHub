package com.yun.demo.nio;

import java.nio.ByteBuffer;

/**
 * @author Administrator
 * @date 2021/6/7 20:38
 */
public class Demo1 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(1024);

        System.out.println("放入前参数");
        System.out.println("position " + byteBuffer.position());
        System.out.println("limit " + byteBuffer.limit());
        System.out.println("capacity " + byteBuffer.capacity());
        System.out.println();

        System.out.println("------put()------");
        System.out.println("放入3个数据");
        byte bt = 1;
        byteBuffer.put(bt);
        byteBuffer.put(bt);
        byteBuffer.put(bt);

        System.out.println("放入后参数");
        System.out.println("position " + byteBuffer.position());
        System.out.println("limit " + byteBuffer.limit());
        System.out.println("capacity " + byteBuffer.capacity());
        System.out.println();

        System.out.println("------flip()-get()------");
        System.out.println("读取一个数据");
        // 切换模式
        byteBuffer.flip();
        byteBuffer.get();

        System.out.println("读取后参数");
        System.out.println("position " + byteBuffer.position());
        System.out.println("limit " + byteBuffer.limit());
        System.out.println("capacity " + byteBuffer.capacity());
        System.out.println();

        System.out.println("------rewind()------");
        byteBuffer.rewind();
        System.out.println("恢复后参数");
        System.out.println("position " + byteBuffer.position());
        System.out.println("limit " + byteBuffer.limit());
        System.out.println("capacity " + byteBuffer.capacity());
        System.out.println();

        System.out.println("------clear()------");
        // 清空缓冲区，这里只是恢复了各个属性的值，但是缓冲区里的数据依然存在
        // 但是下次写入的时候会覆盖缓冲区中之前的数据
        byteBuffer.clear();
        System.out.println("清空后参数");
        System.out.println("position " + byteBuffer.position());
        System.out.println("limit " + byteBuffer.limit());
        System.out.println("capacity " + byteBuffer.capacity());
        System.out.println();
        System.out.println("清空后获得数据");
        System.out.println(byteBuffer.get());

    }
}
