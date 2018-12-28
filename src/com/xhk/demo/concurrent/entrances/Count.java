package com.xhk.demo.concurrent.entrances;

import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-26 17:57
 */
public class Count {

	private int count = 0;

	private Random random = new Random();

	public synchronized int increment() {
		int temp = count;
		if (random.nextBoolean())
			Thread.yield();
		return count = ++temp;
	}

	public synchronized int value() {
		return count;
	}
}
