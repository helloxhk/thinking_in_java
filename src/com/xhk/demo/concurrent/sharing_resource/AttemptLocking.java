package com.xhk.demo.concurrent.sharing_resource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xhk
 * @time 2018-12-26 13:18
 */
public class AttemptLocking {

	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock() " + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS " + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking al = new AttemptLocking();
		al.untimed(); // true
		al.timed(); // true

		new Thread(){
			{ setDaemon(true); }

			@Override
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();

//		Thread.sleep(100);
		Thread.yield();

		al.untimed(); // false
		al.timed(); // false
	}
}
