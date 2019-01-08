package com.xhk.demo.concurrent.car_factory;

/**
 * @author xhk
 * @time 2019-01-07 17:58
 */
public class Car {

	private final int id;

	private boolean
		engine, driveTrain, wheels;

	public Car(int id) {
		this.id = id;
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void addEngine() {
		engine = true;
	}

	public synchronized void addDrivenTrain() {
		driveTrain = true;
	}

	public synchronized void addWheels() {
		wheels = true;
	}

	@Override
	public String toString() {
		return "Car " + id + " [ engine : " + engine + "\tdrivenTrain : " + driveTrain + "\twheels : " + wheels + " ] ";
	}
}
