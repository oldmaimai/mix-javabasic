package com.huaan.javabasic.tools;

import java.util.ArrayList;
import java.util.List;

public class IntelliJDebug {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = sum(a, b);
        System.out.println("====== " + c);
        for (int i = 0; i < 10; i++)
        {
            System.out.println("i -> " + i);
        }
        List<SomeClass> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            SomeClass aa = new SomeClass();
            aa.toString();
            list.add(aa);
        }
    }

    private static int sum(int i, int j) {
       return i + j;
    }

}

class SomeClass
{
    private byte[] aa = new byte[1024 * 1024];;
}
