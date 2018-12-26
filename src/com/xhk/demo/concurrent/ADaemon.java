package com.xhk.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-25 14:49
 */
public class ADaemon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("start a Daemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("always run ? ");
		}
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new ADaemon());
		thread.setDaemon(true);
		thread.start();
	}
}
