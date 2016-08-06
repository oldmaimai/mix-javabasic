package com.huaan.javabasic.concurrent;

import java.util.ArrayList;
import java.util.List;

public class TestFor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        for (String str : manyStrings())
        {
        	System.out.println(str);
        }
	}
	
	private static List<String> manyStrings()
	{
		System.out.println(" === in manyStrings ===");
		List<String> strs = new ArrayList<String>();
		strs.add("one");
		strs.add("two");
		strs.add("three");
		return strs;
	}

}
