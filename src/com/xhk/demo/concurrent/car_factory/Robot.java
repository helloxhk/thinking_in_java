package com.xhk.demo.concurrent.car_factory;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author xhk
 * @time 2019-01-07 18:12
 */
public abstract class Robot implements Runnable {

	private RobotPool robotPool;

	public Robot(RobotPool robotPool) {
		this.robotPool = robotPool;
	}

	protected Assembler assembler;

	public Robot assignAssembler(Assembler assembler) {
		this.assembler = assembler;
		return this;
	}

	private boolean engine;

	public synchronized void engine() {
		this.engine = true;
		notifyAll();
	}

	@Override
	public void run() {
		try {
			powerDown();
			while (!Thread.interrupted()) {
				performService();
				assembler.barrier.await();
				powerDown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public synchronized void powerDown() throws InterruptedException {
		engine = false;
		assembler = null;
		robotPool.release(this);
		while (!engine)
			wait();
	}

	public abstract void performService();

	@Override
	public String toString() {
		return getClass().getName();
	}
}

class EngineRobot extends Robot {

	public EngineRobot(RobotPool robotPool) {
		super(robotPool);
	}

	@Override
	public void performService() {
		System.out.println(this + " installing Engine.");
		assembler.car.addEngine();
	}
}

class WheelsRobot extends Robot {

	public WheelsRobot(RobotPool robotPool) {
		super(robotPool);
	}

	@Override
	public void performService() {
		System.out.println(this + " installing Wheels.");
		assembler.car.addWheels();
	}
}

class DriveTrainRobot extends Robot {

	public DriveTrainRobot(RobotPool robotPool) {
		super(robotPool);
	}

	@Override
	public void performService() {
		System.out.println(this + " installing DriveTrain.");
		assembler.car.addDrivenTrain();
	}
}
