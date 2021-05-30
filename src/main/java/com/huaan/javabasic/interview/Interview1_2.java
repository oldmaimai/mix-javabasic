package com.huaan.javabasic.interview;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Interview1_2 {
    private static String fileName = "D:\\bigfile.txt";
    private static String content = ", a, b" + System.lineSeparator();
    public static void main(String[] args) throws IOException {
        int totalLine = 100000000;
        genBigFile(totalLine);
        long begin = System.currentTimeMillis();
        String line1 = readNthLine1(totalLine/2);
        System.out.println("readNthLine1 cost : " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        String line2 = readNthLine2(totalLine/2);
        System.out.println("readNthLine2 cost : " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        String line3 = readNthLine3(totalLine/2);
        System.out.println("readNthLine3 cost : " + (System.currentTimeMillis() - begin));
        System.out.println("line1:" + line1 + " line2:" + line2 + " line3:" + line3);
    }

    private static void genBigFile(int totalLine) throws IOException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            for (int i = 0; i < totalLine; i++)
            writer.write(i + content);
        }
    }

    private static String readNthLine1(int n) throws IOException {
        String line = Files.readAllLines(Paths.get(fileName)).get(n);
        return line;
    }

    private static String readNthLine2(int n) throws IOException {
        String line;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            line = lines.skip(n).findFirst().get();
        }
        return line;
    }

    private static String readNthLine3(int n) throws IOException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < n; i++)
                br.readLine();
            line = br.readLine();
        }
        return line;
    }
}
