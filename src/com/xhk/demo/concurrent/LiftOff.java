package com.xhk.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-25 11:01
 */
public class LiftOff implements Runnable {

	private static int taskCount = 0;

	private int countDown = 10;

	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	private String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff") + ")";
	}

	@Override
	public void run() {
//		synchronized (LiftOff.class){
			while (countDown-- > 0) {
				System.out.print(status());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				Thread.yield();
			}
			System.out.println();
//		}
	}

	public static void main(String[] args) {
		/*LiftOff liftOff = new LiftOff();
		new Thread(liftOff).start();*/
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
	}
}
