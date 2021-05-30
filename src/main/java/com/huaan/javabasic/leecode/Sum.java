package com.huaan.javabasic.leecode;

import java.util.HashMap;
import java.util.Map;

public class Sum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        Printer.printArray(new Sum().twoSum2(nums, target));
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            Integer position = map.get(temp);
            if (position != null) {
                return new int[]{position, i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
