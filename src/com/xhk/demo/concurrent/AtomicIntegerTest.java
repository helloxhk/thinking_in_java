package com.xhk.demo.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xhk
 * @time 2018-12-26 15:05
 */
public class AtomicIntegerTest implements Runnable {

	private AtomicInteger at = new AtomicInteger(0);

	public void increment() {
		at.addAndGet(2);
	}

	public int get() {
		return at.get();
	}

	@Override
	public void run() {
		while (true)
			increment();
	}

	public static void main(String[] args) {

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("5000 seconds later.");
			}
		}, 5000);

		ExecutorService executors = Executors.newCachedThreadPool();
		AtomicIntegerTest att = new AtomicIntegerTest();
		for (int i = 0; i < 5; i++) {
			executors.execute(att);
		}

		while (true) {
			int i = att.get();
			if (i % 2 != 0) {
				System.out.println(i + " is not even");
				System.exit(0);
			}
		}
	}
}
