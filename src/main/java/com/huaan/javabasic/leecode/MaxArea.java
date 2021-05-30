package com.huaan.javabasic.leecode;

public class MaxArea {
    public static void main(String[] args) {
        int[] height = new int[]{1, 8};
        new MaxArea().maxArea(height);
    }

    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1, max = 0;
        while (l < r) {
            max = Math.max(max, area(new int[]{l, height[l]}, new int[]{r, height[r]}));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;

    }
        public int maxArea(int[] height) {
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            for (int j = i + 1; j <= height.length; j++) {
                max = Math.max(max, area(new int[]{i, height[i - 1]}, new int[]{j, height[j - 1]}));
            }
        }
        return max;
    }

    public int area(int[] left, int[] right) {
        return (right[0] - left[0]) * Math.min(left[1], right[1]);
    }
}
