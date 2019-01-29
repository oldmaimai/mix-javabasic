package com.huaan.javabasic.tools;

import java.io.File;

public class DirectoryDelete {
    private static long sum = 0;
    public static void main(String[] args) {
        //String baseDir = "E:\\aaa\\0924-1-37";
        String baseDir = "G:\\aaa";
        for (File file : new File(baseDir).listFiles()) {
            if (file.isDirectory())
            {
                // 列出当前目录的所有文件大小之和
                sum = 0;
                long sum1 = getAllFilesSize(file);
                // 找到50M以下的文件夹
                if (sum1 < 1024 * 1024 *50)
                {
                    System.out.println( "rm -rf " + file.getName());
//                    if (!file.delete())
//                    {
//                        System.out.println("can not delete " + file);
//                    }
                }
            }
        }
    }

    private static long getAllFilesSize(File dir) {
        //System.out.println("文件"+dir.getName()+"的大小是："+dir.length());
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                //这里面用了递归的算法
                getAllFilesSize(files[i]);
            } else {
                sum += files[i].length();
                //System.out.println(files[i] + "的大小：" + files[i].length());
            }
        }
        return sum;
    }

}
