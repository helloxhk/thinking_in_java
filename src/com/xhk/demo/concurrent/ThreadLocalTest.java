package com.xhk.demo.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-26 16:52
 */
public class ThreadLocalTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executors.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		executors.shutdownNow();
	}
}

class Accessor implements Runnable {

	private int id;

	public Accessor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}

	@Override
	public String toString() {
		return "#" + id + " - " + ThreadLocalVariableHolder.getValue();
	}
}

class ThreadLocalVariableHolder {

	private static ThreadLocal<Integer> tl = new ThreadLocal() {
		private Random random = new Random();
		@Override
		protected Integer initialValue() {
			return random.nextInt(10000);
		}
	};

	public static void increment() {
		tl.set(tl.get() + 1);
	}

	public static int getValue() {
		return tl.get();
	}
}
