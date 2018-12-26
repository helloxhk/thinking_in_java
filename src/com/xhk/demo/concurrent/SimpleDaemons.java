package com.xhk.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-25 14:29
 */
public class SimpleDaemons implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + "" + this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("All Daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
