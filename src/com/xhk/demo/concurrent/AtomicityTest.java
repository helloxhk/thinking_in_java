package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xhk
 * @time 2018-12-26 14:00
 */
public class AtomicityTest implements Runnable {

	private volatile int num = 0;

	public synchronized void increment() {
		num++;
		num++;
	}

	public synchronized int getNum() {
		return num;
	}

	@Override
	public void run() {
		while (true)
			increment();
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		AtomicityTest at = new AtomicityTest();
		executor.execute(at);

		while (true) {
			int num = at.getNum();
			if (num % 2 != 0) {
				System.out.println(num);
				System.exit(0);
			}
		}
	}
}
