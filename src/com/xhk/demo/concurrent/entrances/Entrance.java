package com.xhk.demo.concurrent.entrances;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-26 18:01
 */
public class Entrance implements Runnable {

	private int id;

	private int number;

	private static Count count = new Count();

	private static List<Entrance> entrances = new ArrayList<>();

	private static volatile boolean cancled = false;

	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}

	public static void cancle() {
		cancled = true;
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumNumbers() {
		int result = 0;
		for (Entrance entrance : entrances) {
			result += entrance.number;
		}
		return result;
	}

	@Override
	public void run() {
		while (!cancled) {
			synchronized (this) {
				number++;
			}
			System.out.println(this + " total " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stopping " + this);
	}

	@Override
	public String toString() {
		return id + " number " + number;
	}
}
