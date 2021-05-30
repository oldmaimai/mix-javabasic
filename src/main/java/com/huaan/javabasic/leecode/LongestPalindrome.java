package com.huaan.javabasic.leecode;

public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "babad";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        if (isPalindrome(s)) {
            return s;
        }

        char[] chars = s.toCharArray();
        String maxEndingHere = String.valueOf(chars[0]);
        String maxSoFar = maxEndingHere;

        for (int i = 1; i < chars.length; i++) {
            if (isPalindrome(maxEndingHere + chars[i])) {
                maxEndingHere = (maxEndingHere + chars[i]);
            } else {
                maxEndingHere = String.valueOf(chars[i]);
            }

            if (maxSoFar.length() < maxEndingHere.length()) {
                maxSoFar = maxEndingHere;
            }
        }
        return maxSoFar;
    }

    private boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().equals(s);
    }
}
