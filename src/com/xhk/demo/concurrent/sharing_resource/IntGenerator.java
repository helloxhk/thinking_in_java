package com.xhk.demo.concurrent.sharing_resource;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xhk
 * @time 2018-12-25 18:21
 */
public abstract class IntGenerator {

	private volatile boolean cancled = false;

	public abstract int next();

	public void cancel() {
		cancled = true;
	}

	public boolean isCancled() {
		return cancled;
	}
}

class EvenGenerator extends IntGenerator {

	private volatile int currentInt = 0;

	@Override
	public /*synchronized*/ int next() {
		++currentInt;
		++currentInt;
		return currentInt;
	}
}

class MutexEvenGenerator extends IntGenerator {

	private volatile int currentInt = 0;

	private Lock lock = new ReentrantLock();

	@Override
	public int next() {
		lock.lock();
		try {
			++currentInt;
			Thread.yield();
			++currentInt;
			return currentInt;
		} finally {
			lock.unlock();
		}
	}
}
