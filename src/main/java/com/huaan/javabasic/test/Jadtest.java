package com.huaan.javabasic.test;

import org.apache.commons.lang.StringUtils;

public class Jadtest {
	public static void main(String[] args) {
		String str = "abc";
		if (StringUtils.isNotEmpty(str))
		{
			System.out.println(StringUtils.reverse(str));
		}
	}

}
