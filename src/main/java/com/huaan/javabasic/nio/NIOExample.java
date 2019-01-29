package com.huaan.javabasic.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOExample {

    public static void main(String[] args) throws IOException {
        // 1. 1.5G 文件复制对比
        String src = "D:\\software\\ubuntu\\ubuntu-16.04.1-desktop-amd64.iso";
        String niodest = "D:\\software\\ubuntu\\ubuntu-16.04.1-desktop-amd64_nio.iso";
        String biodest = "D:\\software\\ubuntu\\ubuntu-16.04.1-desktop-amd64_bio.iso";
        long start = System.currentTimeMillis();
        copyFileUseNIO(src, niodest);
        System.out.println("noi cost : " + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        copyFileUseBIO(src, biodest);
        System.out.println("boi cost : " + (System.currentTimeMillis() - start) + "ms");


    }

    private static void copyFileUseBIO(String src, String dst) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(src);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(dst);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 用java NIO api拷贝文件
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    private static void copyFileUseNIO(String src, String dst) throws IOException {
        //声明源文件和目标文件
        FileInputStream fi = new FileInputStream(new File(src));
        FileOutputStream fo = new FileOutputStream(new File(dst));
        //获得传输通道channel
        FileChannel inChannel = fi.getChannel();
        FileChannel outChannel = fo.getChannel();
        //获得容器buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            //判断是否读完文件
            int eof = inChannel.read(buffer);
            if (eof == -1) {
                break;
            }
            //重设一下buffer的position=0，limit=position
            buffer.flip();
            //开始写
            outChannel.write(buffer);
            //写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();
    }

}


