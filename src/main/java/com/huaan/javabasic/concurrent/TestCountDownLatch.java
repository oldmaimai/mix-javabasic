package com.huaan.javabasic.concurrent;

import java.util.concurrent.CountDownLatch;
import java.lang.Runtime;
/**
 * 测试同步器CountDownLatch
 * @author maihuaan
 * Date:2016年1月6日下午4:45:43 
 * Copyright (c) 2016, maihuaan@126.com All Rights Reserved.
 */
public class TestCountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		Thread t1 = new Thread(new Worker("张三", 4000, latch));
		Thread t2 = new Thread(new Worker("李四", 8000, latch));
		t1.start();
		t2.start();
		latch.await();
		System.out.println("all work done!");
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

}

class Worker implements Runnable
{
	String workerName;
	int workTime;
	CountDownLatch latch;
	
	public Worker(String workerName, int workTime, CountDownLatch latch) {
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			System.out.println(workerName + "begin to work...");
			Thread.sleep(workTime);
			System.out.println(workerName + "work done!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			latch.countDown();			
		}
	}
	
}
