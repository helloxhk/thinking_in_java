package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-26 14:25
 */
public class SerialNumberGenerator {

	private static volatile int number = 0;

	public synchronized static int nextSerialNumber() {
		return ++number;
	}

}

class CircularSet {

	private int[] array;

	private int length;

	private int index = 0;

	public CircularSet(int size) {
		this.array = new int[size];
		this.length = size;
	}

	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % length;
	}

	public synchronized boolean contains(int i) {
		for (int j : array) {
			if (j == i)
				return true;
		}
		return false;
	}

	public static class SerialNumberChecker{

		private static final CircularSet set = new CircularSet(1000);

		static class SerialChecker implements Runnable {
			@Override
			public void run() {
				while (true) {
					int serial = SerialNumberGenerator.nextSerialNumber();
//					System.out.println("normal " + serial);
					if (set.contains(serial)) {
						System.out.println("duplicate " + serial);
						System.exit(0);
					}
					set.add(serial);
				}
			}
		}

		public static void main(String[] args) {
			ExecutorService executors = Executors.newCachedThreadPool();
			for (int i = 0; i < 10; i++) {
				executors.execute(new SerialChecker());
			}

			try {
				TimeUnit.SECONDS.sleep(5);
				System.out.println("5秒钟没有出现重复数字");
				System.exit(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
