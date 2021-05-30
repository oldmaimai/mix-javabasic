package com.huaan.javabasic.leecode;

public class FindMedianSortedArrays {
    public static void main(String[] args) {
        System.out.println();
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] concat = new int[nums1.length + nums2.length];
        //System.arraycopy(nums1, 0, concat, 0, nums1.length);
        //System.arraycopy(nums2, 0, concat, nums1.length, nums2.length);

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                concat[k] = nums1[i];
                i++;
            } else {
                concat[k] = nums2[j];
                j++;
            }
            k++;
        }

        if (i == nums1.length) {
            System.arraycopy(nums2, j, concat, k, nums2.length - j);
        } else {
            System.arraycopy(nums1, i, concat, k, nums1.length - i);
        }


        i = 0;
        j = concat.length - 1;

        while (i < j) {
            i++;
            j--;
        }

        if (i == j) {
            return concat[i];
        }
        else {
            return (concat[i] + concat[j]) / 2.0;
        }

    }
}
