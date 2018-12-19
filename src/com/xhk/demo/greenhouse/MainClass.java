package com.xhk.demo.greenhouse;

import java.util.Date;

/**
 * @author xhk
 * @time 2018-11-28 14:41
 */
public class MainClass {

	public static void main(String[] args) throws InterruptedException {

		/*GreenhouseController gc = new GreenhouseController();

		gc.addEvent(gc.new Bell(9000000000111000000L));

		Event[] events = {
				gc.new ThermostatNight(0),
				gc.new LightOn(200),
				gc.new LightOff(400),
				gc.new WaterOn(600),
				gc.new WaterOn(800),
				gc.new ThermostatDay(1400)
		};

		gc.addAll(Arrays.asList(events));

		gc.addEvent(gc.new Restart(500000000000000L, Arrays.asList(events)));

		gc.run();*/

		// 99320130
		// 99530416

		long begin = System.nanoTime();
		long end = System.currentTimeMillis();
		System.out.println(new Date(1543391629405L).toLocaleString());

//		System.out.println(begin);
//		System.out.println(end);
	}

}
