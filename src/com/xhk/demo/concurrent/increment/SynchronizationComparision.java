package com.xhk.demo.concurrent.increment;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xhk
 * @time 2019-01-08 13:12
 */
public class SynchronizationComparision {

	private static BaseLine baseLine = new BaseLine();

	private static SynchronizedTest2 synchronizedTest = new SynchronizedTest2();

	private static LockTest2 lockTest2 = new LockTest2();

	private static AtomicTest atomicTest = new AtomicTest();

	static void test() {
		System.out.println("======================================");
		System.out.println(String.format("%-12s : %13d\n","cycles",Accumulator.cycles));
		baseLine.timeTest();
		synchronizedTest.timeTest();
		lockTest2.timeTest();
		atomicTest.timeTest();
	}

	public static void main(String[] args) {
		baseLine.timeTest();

		for (int i = 0; i < 5; i++) {
			test();
			Accumulator.cycles *= 2;
		}
		Accumulator.executor.shutdown();
		System.out.println("over");
	}
}

abstract class Accumulator {

	public static long cycles = 50000;

	private static final int N = 4;

	public static ExecutorService executor = Executors.newFixedThreadPool(N * 2);

	private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);

	protected volatile int index = 0;

	protected volatile long value = 0;

	protected long duration = 0;

	protected String id = "error";

	protected final static int SIZE = 100000;

	protected static int[] preLoaded = new int[SIZE];

	protected volatile int x = 1;

	static {
		Random random = new Random();
		for (int i = 0; i < SIZE; i++) {
			preLoaded[i] = random.nextInt();
		}
	}

	public abstract void accumulate();

	public abstract long read();

	private class Modifier implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < cycles; i++) {
				accumulate();
			}
			try {
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private class Reader implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < cycles; i++) {
				read();
			}
			try {
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void timeTest() {
		long start = System.nanoTime();
		for (int i = 0; i < N; i++) {
			executor.execute(new Modifier());
			executor.execute(new Reader());
		}
		try {
			barrier.await();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		duration = System.nanoTime() - start;
		System.out.println(String.format("%-13s : %13d\n", id, duration));
	}
}

class BaseLine extends Accumulator {

	{
		id = "BaseLine";
	}

	@Override
	public void accumulate() {
		value += preLoaded[index];
		if (index >= SIZE)
			index = 0;
	}

	@Override
	public long read() {
		return value;
	}
}

class SynchronizedTest2 extends Accumulator {

	{
		id = "Synchronized";
	}

	@Override
	public synchronized void accumulate() {
		value += preLoaded[index];
		if (index >= SIZE)
			index = 0;
	}

	@Override
	public synchronized long read() {
		return value;
	}
}

class LockTest2 extends Accumulator {

	{
		id = "Lock";
	}

	private Lock lock = new ReentrantLock();

	@Override
	public void accumulate() {
		lock.lock();
		try {
			value += preLoaded[index];
			if (index >= SIZE)
				index = 0;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public long read() {
		lock.lock();
		try {
			return value;
		} finally {
			lock.unlock();
		}
	}
}

class AtomicTest extends Accumulator {

	{
		id = "Atomic";
	}

	private AtomicInteger index = new AtomicInteger(0);
	private AtomicLong value = new AtomicLong(0);

	@Override
	public void accumulate() {
		int i = index.getAndIncrement();
		if (i >= SIZE) {
			i = 0;
			index.set(0);
		}
		value.getAndAdd(preLoaded[i]);
	}

	@Override
	public long read() {
		return value.get();
	}
}