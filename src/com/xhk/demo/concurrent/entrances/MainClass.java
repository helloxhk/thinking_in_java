package com.xhk.demo.concurrent.entrances;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-26 18:10
 */
public class MainClass {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executors.execute(new Entrance(i));
		}

		TimeUnit.SECONDS.sleep(3);
		Entrance.cancle();

		executors.shutdown();
		if (!executors.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("some tasks were not terminated.");
		}
		System.out.println("Total " + Entrance.getTotalCount());
		System.out.println("SumNumbers " + Entrance.sumNumbers());

		System.out.println("******************************");

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

	}
}
