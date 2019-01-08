package com.xhk.demo.concurrent.car_factory;

/**
 * @author xhk
 * @time 2019-01-08 10:15
 */
public class Reporter implements Runnable {

	private CarQueue carQueue;

	public Reporter(CarQueue carQueue) {
		this.carQueue = carQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(carQueue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Reporter off");
	}
}
