package com.huaan.javabasic.leecode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers twoNumbers = new AddTwoNumbers();
        long a = 9999999999L;
        long b = 9999999999L;
//        System.out.println(twoNumbers.addTwoNumbers());
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return intToList(listToInt(l1) + listToInt(l2));
    }

    private long listToInt(ListNode node) {
        long result = 0;
        ListNode currentNode = node;
        int i = 0;
        do {
            result = currentNode.val * (long)Math.pow(10, i) + result;
            currentNode = currentNode.next;
            i++;

        } while (currentNode != null);
        return result;
    }

    private ListNode intToList(long value) {
        String strVal = new StringBuilder(String.valueOf(value)).reverse().toString();
        ListNode node = null;
        ListNode currentPoint = null;
        for (char c : strVal.toCharArray()) {
            ListNode temp = new ListNode(Integer.parseInt(c + ""));
            if (currentPoint == null) {
                node = temp;
            } else {
                currentPoint.next = temp;
            }
            currentPoint = temp;
        }
        return node;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }