package com.xhk.demo.greenhouse;

import java.util.List;

/**
 * 温室控制器
 * @author xhk
 * @time 2018-11-28 10:12
 */
public class GreenhouseController extends Controller {

	private boolean light = false;

	public class LightOn extends Event {

		public LightOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			light = true;
		}

		@Override
		public String toString() {
			return "light is on";
		}
	}

	public class LightOff extends Event {

		public LightOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			light = false;
		}

		@Override
		public String toString() {
			return "light is off";
		}
	}

	private boolean water = false;

	public class WaterOn extends Event {

		public WaterOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = true;
		}

		@Override
		public String toString() {
			return "greenhouse water is on";
		}
	}

	public class WaterOff extends Event {

		public WaterOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = false;
		}

		@Override
		public String toString() {
			return "greenhouse water is off";
		}
	}

	private String thermostat = "Day";

	public class ThermostatNight extends Event {

		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Night";
		}

		@Override
		public String toString() {
			return "thermostat on Night setting";
		}
	}

	public class ThermostatDay extends Event {

		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Day";
		}

		@Override
		public String toString() {
			return "thermostat on Day setting";
		}
	}

	public class Bell extends Event {

		public Bell(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			addEvent(new Bell(delayTime));
		}

		@Override
		public String toString() {
			return "Duang ! ~~ `";
		}
	}

	public class Restart extends Event {

		private List<Event> eventList;

		public Restart(long delayTime, List<Event> eventList) {
			super(delayTime);
			this.eventList = eventList;
			for (Event event : eventList)
				addEvent(event);
		}

		@Override
		public void action() {
			for (Event event : eventList) {
				event.start();
				addEvent(event);
			}
			start();
			addEvent(this);
		}

		@Override
		public String toString() {
			return "Restarting system";
		}
	}

	public static class Terminate extends Event {

		public Terminate(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}

		@Override
		public String toString() {
			return "Terminating";
		}
	}
}
