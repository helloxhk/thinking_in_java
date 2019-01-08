package com.xhk.demo.concurrent.increment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xhk
 * @time 2019-01-08 11:08
 */
public abstract class Incrementable {

	protected long counter = 0;

	public abstract void increment();
}

class SynchronizedTest extends Incrementable {

	@Override
	public synchronized void increment() {
		counter++;
	}
}

class LockTest extends Incrementable {

	private Lock lock = new ReentrantLock();

	@Override
	public void increment() {
		lock.lock();
		try {
			counter++;
		} finally {
			lock.unlock();
		}
	}
}

class Test1 {

	static long test(Incrementable incrementable) {
		long start = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			incrementable.increment();
		}
		return System.nanoTime() - start;
	}

	public static void main(String[] args) {
		long synchroTime = test(new SynchronizedTest());
		long lockTime = test(new LockTest());
		System.out.println(synchroTime);
		System.out.println(lockTime);
	}
}
