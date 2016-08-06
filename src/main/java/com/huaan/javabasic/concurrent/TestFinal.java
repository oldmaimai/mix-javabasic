package com.huaan.javabasic.concurrent;

import java.util.HashSet;
import java.util.Set;

public class TestFinal {
	private final Set<String> stooges = new HashSet<String>();
	public TestFinal()
	{
		stooges.add("aa");
		stooges.add("bb");
	}
	
	public Set<String> addStooge()
	{
		//stooges = new HashSet<String>();
		stooges.add("cc");
		return stooges;
	}

}
