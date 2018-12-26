package com.xhk.demo.concurrent;

/**
 * @author xhk
 * @time 2018-12-25 15:03
 */
public class SelfManage implements Runnable {

	private int countDown = 5;

	private Thread thread = new Thread(this);

	public SelfManage() {
		thread.start();
	}

	@Override
	public String toString() {
		return Thread.currentThread() + "" + countDown;
	}

	@Override
	public void run() {
		while (true) {
			System.out.print(this);
			if (--countDown == 0) {
				System.out.println();
				System.out.println();
				return;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SelfManage();
		}
	}
}
