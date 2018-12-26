package com.xhk.demo.concurrent;

/**
 * @author xhk
 * @time 2018-12-25 14:58
 */
public class SimpleThread extends Thread {

	private int countDown = 5;

	private static int threadCount;

	public SimpleThread() {
		super(Integer.toString(++threadCount));
		start();
	}

	@Override
	public String toString() {
		return "#" + getName() + "(" + countDown + ")";
	}

	@Override
	public void run() {
		while (true) {
			System.out.print(this);
			if (--countDown == 0) {
				System.out.println();
				return;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}
}
