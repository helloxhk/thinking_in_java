package com.xhk.demo.concurrent.car_factory;

import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-07 18:04
 */
public class ChassisBuilder implements Runnable {

	private CarQueue carQueue;

	private int counter = 0;

	public ChassisBuilder(CarQueue carQueue) {
		this.carQueue = carQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				Car car = new Car(counter++);
				System.out.println("ChassisBuilder create " + car);
				carQueue.put(car);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted ChassisBuilder");
		}
		System.out.println("ChassisBuilder off");
	}
}
