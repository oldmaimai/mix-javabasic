package com.huaan.javabasic.leecode.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * https://mp.weixin.qq.com/s/6DMLl_EksTqSqWyE2FkEug
 */
public class Expression {
    public static void main(String[] args) {
        //  testStack();
        // 计算 1 + 2 * 4 -6
        String exp = "1+2*4-6+4/2-1";
        System.out.println(calc(exp));
    }

    private static int calc(String exp) {
        StackBaseLinkList num = new StackBaseLinkList();
        StackBaseLinkList opr = new StackBaseLinkList();
        // 定义优先级
        Map<String, Integer> oprPres = new HashMap<>();
        oprPres.put("+", 1);
        oprPres.put("-", 1);
        oprPres.put("*", 2);
        oprPres.put("/", 2);

        char[] chars = exp.toCharArray();

        for (int i = 0; i < chars.length; i++) {
             char aChar = chars[i];

            if (Character.isDigit(aChar)) {
                num.push(String.valueOf(aChar));

                // 最后一个数字，进行最后的计算
                if (i == chars.length - 1) {
                    int right = Integer.parseInt(num.pop());
                    int left = Integer.parseInt(num.pop());
                    String op = opr.pop();
                    return calcSimple(left, right, op);
                }
            } else {
                // 运算符
                while (opr.top() != null && oprPres.get(String.valueOf(aChar)) <= Integer.valueOf(oprPres.get(opr.top()))) {
                    int right = Integer.parseInt(num.pop());
                    int left = Integer.parseInt(num.pop());
                    String op = opr.pop();
                    num.push(String.valueOf(calcSimple(left, right, op)));
                }

                opr.push(String.valueOf(aChar));
            }
        }
        return 0;
    }

    private static int calcSimple(int left, int right, String opr) {
        int val = 0;
        switch (opr) {
            case "+": val = left + right; break;
            case "-": val = left - right; break;
            case "*": val = left * right; break;
            case "/": val = left / right; break;
            default:
                throw new IllegalArgumentException();
        }
        return val;
    }

    private static void testStack() {
        StackBaseLinkList stack = new StackBaseLinkList();
        stack.push("a");
        stack.push("b");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

/**
 * 基于链表的栈
 */
class StackBaseLinkList {
    private Node top;

    public String top() {
        if (top == null) {
            return null;
        } else {
            return top.value;
        }
    }

    public void push(String val) {
        Node node = new Node(val);
        node.next = top;
        top = node;
    }

    public String pop() {
        if (top == null) {
            return null;
        } else {
            String val = top.value;
            top = top.next;
            return val;
        }
    }

    private static class Node {
        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
        }

    }
}