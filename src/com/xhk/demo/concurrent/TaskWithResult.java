package com.xhk.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xhk
 * @time 2018-12-25 13:37
 */
public class TaskWithResult implements Callable<String> {

	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			futures.add(executor.submit(new TaskWithResult(i + 1)));
		}

		for (Future<String> future : futures) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				executor.shutdown();
			}
		}
	}
}
