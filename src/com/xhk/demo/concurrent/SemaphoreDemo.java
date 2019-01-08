package com.xhk.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xhk
 * @time 2019-01-07 13:39
 */
public class SemaphoreDemo {

	private static final int SIZE = 25;

	public static void main(String[] args) throws InterruptedException {
		Pool<Fat> pool = new Pool<>(Fat.class, SIZE);

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++) {
			executor.execute(new CheckOutTask<>(pool));
		}
		System.out.println("All CheckOutTasks created.");

		List<Fat> list = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			Fat fat = pool.checkOut();
			System.out.println("Main thread check out.");
			fat.opration();
			list.add(fat);
		}

		Future<?> future = executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					pool.checkOut();
				} catch (InterruptedException e) {
					System.out.println("checkout interrupted");
				}
			}
		});

		TimeUnit.SECONDS.sleep(2);

		future.cancel(true);

		for (Fat fat : list) {
			pool.checkIn(fat);
		}

		for (Fat fat : list) {
			pool.checkIn(fat);
		}
		executor.shutdown();
	}
}

class Pool<T> {

	private int size;

	private List<T> items = new ArrayList<>();

	private volatile boolean[] checkOuts;

	private Semaphore semaphore;

	public Pool(Class<T> clazz, int size) {
		this.size = size;
		checkOuts = new boolean[size];
		semaphore = new Semaphore(size, true);
		for (int i = 0; i < size; i++) {
			try {
				items.add(clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public T checkOut() throws InterruptedException {
		semaphore.acquire();
		return getItem();
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; i++) {
			if (!checkOuts[i]) {
				checkOuts[i] = true;
				return items.get(i);
			}
		}
		return null;
	}

	public void checkIn(T item) {
		if (releaseItem(item))
			semaphore.release();
	}

	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if (index == -1)
			return false;
		if (checkOuts[index]) {
			checkOuts[index] = false;
			return true;
		}
		return false;
	}
}

class Fat {

	private volatile double d;

	private static int counter = 0;

	private final int id = counter++;

	public Fat() {
		for (int i = 0; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}

	public void opration() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Fat id : " + id;
	}
}

class CheckOutTask<T> implements Runnable {

	private static int counter = 0;

	private final int id = counter++;

	private Pool<T> pool;

	public CheckOutTask(Pool<T> pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		try {
			T t = pool.checkOut();
			System.out.println(this + " check out " + t);
			TimeUnit.SECONDS.sleep(2);
			System.out.println(this + " check in " + t);
			pool.checkIn(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CheckOutTask id : " + id;
	}
}
