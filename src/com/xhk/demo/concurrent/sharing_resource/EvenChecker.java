package com.xhk.demo.concurrent.sharing_resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xhk
 * @time 2018-12-25 18:23
 */
public class EvenChecker implements Runnable {

	private IntGenerator eg;

	public EvenChecker(IntGenerator eg) {
		this.eg = eg;
	}

	@Override
	public void run() {
		while (!eg.isCancled()) {
			int next = eg.next();
			if (next % 2 != 0) {
				System.out.println(next + " is not even");
				eg.cancel();
			}
		}
	}

	public static void test(IntGenerator eg, int count) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			executor.execute(new EvenChecker(eg));
		}
		executor.shutdown();
	}

	public static void test(IntGenerator eg) {
		test(eg, 10);
	}

	public static void main(String[] args) {
//		EvenChecker.test(new EvenGenerator());
		EvenChecker.test(new MutexEvenGenerator());
	}
}
