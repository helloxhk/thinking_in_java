package com.xhk.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xhk
 * @time 2019-01-04 10:31
 */
public class HorseRace {

	public static final int FINAL_LINE = 20;

	private List<Horse> horses = new ArrayList<>();

	private ExecutorService executor = Executors.newCachedThreadPool();

	private CyclicBarrier barrier;

	public HorseRace(int nHorse, final int pause) {
		this.barrier = new CyclicBarrier(nHorse, new Runnable() {
			@Override
			public void run() {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < FINAL_LINE; i++) {
					sb.append("=");
				}
				System.out.println(sb);
				for (Horse hors : horses) {
					System.out.println(hors.tracks());
				}
				for (Horse hors : horses) {
					if (hors.getStrides() >= FINAL_LINE) {
						System.out.println(hors + " won...");
						executor.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.SECONDS.sleep(pause);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		for (int i = 0; i < nHorse; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			executor.execute(horse);
		}
	}

	public static void main(String[] args) {
		new HorseRace(7, 1);
	}
}

class Horse implements Runnable {

	private static int counter = 0;

	private final int id = counter++;

	private int strides;

	private static Random random = new Random(47);

	private CyclicBarrier barrier;

	public Horse(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 保证其他线程不会操作strides
				synchronized (this) {
					strides += random.nextInt(3);
				}
				barrier.await();
			}
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getStrides() {
		return strides;
	}

	public String tracks() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strides; i++) {
			sb.append("*");
		}
		sb.append(id);
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Horse " + id + " ";
	}
}
