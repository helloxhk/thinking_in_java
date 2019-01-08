package com.xhk.demo.concurrent.car_factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-08 10:12
 */
public class CarBuilder {

	public static void main(String[] args) throws InterruptedException {
		CarQueue
				chassisQueue = new CarQueue(),
				finishedQueue = new CarQueue();
		RobotPool pool = new RobotPool();
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new DriveTrainRobot(pool));
		executor.execute(new EngineRobot(pool));
		executor.execute(new WheelsRobot(pool));
		executor.execute(new Reporter(finishedQueue));
		executor.execute(new Assembler(chassisQueue, finishedQueue, pool));
		executor.execute(new ChassisBuilder(chassisQueue));

		TimeUnit.SECONDS.sleep(7);
		executor.shutdownNow();
	}
}
