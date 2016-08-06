package com.huaan.javabasic.concurrent;

public class StringLockTest {

	public static void main(String[] args) {
		Thread1 mTh1=new Thread1();
		Thread2 mTh2=new Thread2();
		mTh1.start();
		mTh2.start();

	}
	

}

class Thread1 extends Thread{
	private String lock = "abc";
	public void run() {
       synchronized (lock) {
    	   try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       
	}
}

class Thread2 extends Thread{
	private String lock = "abc";
	public void run() {
       synchronized (lock) {
    	   System.out.println("thread 2");
	}
       
	}
}

