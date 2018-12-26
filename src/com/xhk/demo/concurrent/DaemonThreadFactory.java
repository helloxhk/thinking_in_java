package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-25 14:40
 */
public class DaemonThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
}

class DaemonFromFactory implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(101);
				System.out.println(Thread.currentThread() + "" + this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 5; i++) {
			executor.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons created");
		TimeUnit.MILLISECONDS.sleep(177);
	}
}
