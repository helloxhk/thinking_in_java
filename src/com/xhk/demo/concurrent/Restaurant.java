package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-02 18:05
 */
public class Restaurant {

	ExecutorService executor = Executors.newCachedThreadPool();

	Meal meal;

	WaitPerson wait = new WaitPerson(this);

	Chef chef = new Chef(this);

	public Restaurant() {
		executor.execute(wait);
		executor.execute(chef);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
}

class WaitPerson implements Runnable {

	private Restaurant restaurant;

	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if (restaurant.meal == null)
						wait();
				}

				System.out.println("WaitPerson got " + restaurant.meal);

				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interruptted.");
		}
	}
}

class Chef implements Runnable {

	private int count = 0;

	private Restaurant restaurant;

	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if (restaurant.meal != null)
						wait();
				}

				if (++count == 9) {
					System.out.println("out of food, closing...");
					restaurant.executor.shutdownNow();
				}

				System.out.print("Order up! ");

				synchronized (restaurant.wait) {
					restaurant.meal = new Meal(count);
					restaurant.wait.notifyAll();
				}

				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef interruptted.");
		}
	}
}

class Meal {

	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}
