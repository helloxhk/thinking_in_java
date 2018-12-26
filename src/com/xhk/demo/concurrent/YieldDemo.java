package com.xhk.demo.concurrent;

/**
 * @author xhk
 * @time 2018-12-25 11:24
 */
public class YieldDemo implements Runnable {

	private static int count;

	private final int id = count++;

	@Override
	public void run() {
		System.out.println(id + " first");
		Thread.yield();
		System.out.println(id + "second ");
		Thread.yield();
		System.out.println(id + "third ");
		Thread.yield();
		System.out.println(id + "End ");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new YieldDemo()).start();
		}
	}
}
