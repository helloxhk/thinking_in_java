package com.xhk.demo.concurrent;

import com.xhk.demo.concurrent.entrances.Count;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-03 16:50
 */
public class CountDownLatchDemo {

	private static final int SIZE = 100;

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(SIZE);
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executor.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < SIZE; i++) {
			executor.execute(new TaskPortion(latch));
		}
		System.out.println("All task launched.");
		executor.shutdown();
	}
}

class TaskPortion implements Runnable {

	private static int count = 0;

	private final int id = count++;

	private CountDownLatch latch;

	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Random random = new Random();

	private void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
		System.out.println(this + " Completed");
	}

	@Override
	public String toString() {
		return String.format("%1$-3s", id);
	}
}

class WaitingTask implements Runnable {

	private static int count = 0;

	private final int id = count++;

	private CountDownLatch latch;

	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
			System.out.println("Latch barrier passed for " + this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format("WaitingTask %1$-3d", id);
	}
}
