package com.lixing;

import com.google.common.io.Resources;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.lf5.util.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class TestMain {
    private static Object object = new Object();

    public static void main(String[] args) {



        int size = 25;
        for (int p = 1; p < 10; p++) {
            int startOffset = (p - 1) * (size);
            int end = startOffset + (size - 1);
            System.out.println(startOffset + " - " + end);
        }
    }

    public static String replaceHTMLTagForChargeBook(String content) {
        if (StringUtils.isBlank(content))
            return content;
        // 去除头尾全角/半角空格
        content = content.replaceAll("^[\\s　]*|[\\s　]*$", "");
        content = "　　" + content.trim()
                .replaceAll("\\n[\\n]+", "\n")
                .replaceAll("\\n[\\s　]*", "\n　　")
                .replaceAll("<p></p>", "")
                .replaceAll("<p>[\\s　]*", "\n　　")
                .replaceAll("<p>", "")
                .replaceAll("</p>", "");
        return content;
    }

    private static String compress(String data) {
        String finalData = null;
        try {
            //打开字节输出流
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            //打开压缩用的输出流,压缩后的结果放在bout中
            GZIPOutputStream gout = new GZIPOutputStream(bout);
            //写入待压缩的字节数组
            gout.write(data.getBytes("UTF-8"));
            //完成压缩写入
            gout.finish();
            //关闭输出流
            gout.close();
            finalData = bout.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalData;
    }

    /**
     * 快速排序java实现
     * @param a 待排序的数组
     * @param start 排序的起点
     * @param end   排序的终点
     * 第一次排序时，start传0，end传length - 1
     */
    public static void quicksort(int[] a, int start, int end) {
        if (start < 0 || end < 0 || start > end) {
            return;
        }
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && a[i] <= a[j]) {
                j--;
            }
            // 元素互换
            switchParam(a, i, j);

            while (i < j && a[i] <= a[j]) {
                i++;
            }
            // 元素互换
            switchParam(a, i, j);
        }

        // 前段排序
        quicksort(a, 0, i - 1);
        // 后段排序
        quicksort(a, i + 1, end);
    }

    public static void switchParam(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
