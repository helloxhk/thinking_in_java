package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xhk
 * @time 2018-12-25 14:01
 */
public class SimplePriority implements Runnable {

	private int countDown = 5;

	private volatile double d;

	private int priority;

	public SimplePriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return Thread.currentThread() + ":" + countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);

		while (true) {
			for (int i = 0; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(new SimplePriority(Thread.MIN_PRIORITY));
		}
		executor.execute(new SimplePriority(Thread.MAX_PRIORITY));
		executor.shutdown();
	}
}
