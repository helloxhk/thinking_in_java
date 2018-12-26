package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xhk
 * @time 2018-12-25 13:21
 */
public class ExecutorsDemo {

	public static void main(String[] args) {

//		cachedThreadPoll();
//		fixedThreadPool();
		singleThreadPool();
	}

	/**
	 * cachedThreadPool
	 * 一般会创建与所需数量相同的线程，然后在它回收旧线程时，停止创建新的线程。
	 */
	private static void cachedThreadPoll() {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(new LiftOff());
		}
		executor.shutdown();
	}

	/**
	 * fixedThreadPool
	 * 预先创建固定数量的线程。
	 */
	private static void fixedThreadPool() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executor.execute(new LiftOff());
		}
		executor.shutdown();
	}

	/**
	 * singleThreadPool
	 * 单个线程的线程池，如果想singleThreadPool提交了多个任务，那么这些任务将排队执行。
	 * 每个任务都会在下一个任务开始之前执行结束，所有任务将使用同一个线程。
	 */
	private static void singleThreadPool() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executor.execute(new LiftOff());
		}
		executor.shutdown();
	}
}
