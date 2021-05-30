package com.huaan.javabasic.leecode;

public class Reverse {
    public String reverseWords(String s) {
        String[] split = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0 ; i--) {
            sb.append(split[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String reverseWords2(String s) {
        StringBuilder sb = new StringBuilder();


        String[] split = s.trim().split("\\s+");
        for (int i = split.length - 1; i >= 0 ; i--) {
            sb.append(split[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        System.out.println(reverse.reverseWords("  hello world! "));
    }
}
