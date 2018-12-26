package com.xhk.demo.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xhk
 * @time 2018-12-26 15:40
 */
public class CriticalSectionDemo {

	public static void test(PairManager pm1, PairManager pm2) {
		ExecutorService executors = Executors.newCachedThreadPool();
		PairManipulator
				pma1 = new PairManipulator(pm1),
				pma2 = new PairManipulator(pm2);
		PairChecker
				pc1 = new PairChecker(pm1),
				pc2 = new PairChecker(pm2);
		executors.execute(pma1);
		executors.execute(pma2);
		executors.execute(pc1);
		executors.execute(pc2);

		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(pma1);
		System.out.println(pma2);
		System.exit(0);
	}

	public static void main(String[] args) {
		test(new PairManager1(), new PairManager2());
	}
}

class PairChecker implements Runnable {

	private PairManager pm;

	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkStatus();
		}
	}
}

class PairManipulator implements Runnable {

	private PairManager pm;

	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		while (true)
			pm.increment();
	}

	@Override
	public String toString() {
		return pm.getPair() + " ,checkCounter = " + pm.checkCounter.get();
	}
}

class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		pair.incrementX();
		pair.incrementY();
		store(getPair());
	}
}

class PairManager2 extends PairManager {

	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			pair.incrementX();
			pair.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

abstract class PairManager {

	AtomicInteger checkCounter = new AtomicInteger();

	protected Pair pair = new Pair();

	private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

	public synchronized Pair getPair() {
		return new Pair(pair.getX(), pair.getY());
	}

	protected void store(Pair pair) {
		storage.add(pair);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract void increment();
}

class Pair {

	private int x;
	private int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
		new Pair(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	@Override
	public String toString() {
		return "x : " + x + ", y : " + y;
	}

	class PairValuesNotEqualException extends RuntimeException {
		public PairValuesNotEqualException() {
			super("pair values not equal : " + Pair.this);
		}
	}

	public void checkStatus() {
		if (x != y)
			throw new PairValuesNotEqualException();
	}
}