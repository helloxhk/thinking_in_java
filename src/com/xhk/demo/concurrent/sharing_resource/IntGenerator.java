package com.xhk.demo.concurrent.sharing_resource;

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
	public int next() {
		++currentInt;
		++currentInt;
		return currentInt;
	}
}
