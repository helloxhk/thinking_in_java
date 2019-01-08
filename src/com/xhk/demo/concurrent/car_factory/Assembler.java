package com.xhk.demo.concurrent.car_factory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xhk
 * @time 2019-01-07 18:09
 */
public class Assembler implements Runnable {

	private CarQueue chassisQueue, finishedQueue;

	public CyclicBarrier barrier = new CyclicBarrier(4);

	private RobotPool robotPool;

	public Car car;

	public Assembler(CarQueue chassisQueue, CarQueue finishedQueue, RobotPool robotPool) {
		this.chassisQueue = chassisQueue;
		this.finishedQueue = finishedQueue;
		this.robotPool = robotPool;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car = chassisQueue.take();
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WheelsRobot.class, this);
				barrier.await();
				finishedQueue.add(car);
			}
		} catch (InterruptedException e) {
			System.out.println("Assembler interrupted.");
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Assembler stop.");
	}
}
