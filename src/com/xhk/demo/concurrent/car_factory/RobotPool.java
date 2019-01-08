package com.xhk.demo.concurrent.car_factory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xhk
 * @time 2019-01-07 18:11
 */
public class RobotPool {

	private Set<Robot> pool = new HashSet<>();

	public synchronized void add(Robot robot) {
		pool.add(robot);
		notifyAll();
	}

	public synchronized void hire(Class<? extends Robot> robotType, Assembler assembler) {
		try {
			for (Robot robot : pool) {
				if (robot.getClass().equals(robotType)) {
					robot.assignAssembler(assembler);
					robot.engine();
					return;
				}
			}
			wait();
			hire(robotType, assembler);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void release(Robot robot) {
		add(robot);
	}
}
