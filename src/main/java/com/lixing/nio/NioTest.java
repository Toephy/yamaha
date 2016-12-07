package com.lixing.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Toephy on 2016.11.10 15:32
 */
public class NioTest {


    public static String inFilePath = "E:\\aaa\\source.xml";
    public static String outFilePath = "E:\\aaa\\target.xml";

    public static void main(String[] args) {
        try {
            File inFile = new File(inFilePath);
            File outFile = new File(outFilePath);

            FileInputStream inputStream = new FileInputStream(inFile);
            FileOutputStream outputStream = new FileOutputStream(outFile);

            FileChannel inputStreamChannel = inputStream.getChannel();
            FileChannel outputStreamChannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(100);

            int i = 1;
            while (true) {
                buffer.clear();
                int read = inputStreamChannel.read(buffer);

                if (read == -1) {
                    break;
                }

                byte[] array = buffer.array();

                for (byte b : array) {
                    System.out.println(b);
                }

                System.out.println("=======================================");
                i++;
                // 切换为输出模式
                buffer.flip();

                outputStreamChannel.write(buffer);
            }
            System.out.println("loop = " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
