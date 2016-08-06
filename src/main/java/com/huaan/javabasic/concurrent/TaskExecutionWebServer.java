package com.huaan.javabasic.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
	private static final int NTHREADS = 3;
	private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			final String threadIdx = "thread_" + i;
			Runnable task = new Runnable() {
				public void run() {
					try {
						System.out.println("[" + threadIdx + "] start....");
						Thread.sleep((int) (Math.random() * 10000));
						System.out.println("[" + threadIdx + "] end.");
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			};
			exec.execute(task);
		}
	}

}
