package com.xhk.demo.concurrent;

/**
 * @author xhk
 * @time 2018-12-25 16:17
 */
public class Joining {

	public static void main(String[] args) {
		Sleeper
				sleepy = new Sleeper("sleepy", 1500),
				grumpy = new Sleeper("grumpy", 1500);
		Joiner
				dopey = new Joiner("dopey", sleepy),
				doc = new Joiner("doc", grumpy);
		grumpy.interrupt();
	}
}

class Sleeper extends Thread {

	private int time;

	public Sleeper(String name, int time) {
		super(name);
		this.time = time;
		start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println(getName() + "was interrupted. isInterrupted : " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
	}
}

class Joiner extends Thread {

	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}
