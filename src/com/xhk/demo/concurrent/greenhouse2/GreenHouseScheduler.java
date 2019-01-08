package com.xhk.demo.concurrent.greenhouse2;

import javafx.util.converter.DateStringConverter;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-07 10:42
 */
public class GreenHouseScheduler {

	private volatile boolean light = false;

	private volatile boolean water = false;

	private String thermostat = "Day";

	public synchronized String getThermostat() {
		return thermostat;
	}

	public synchronized void setThermostat(String thermostat) {
		this.thermostat = thermostat;
	}

	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

	public void schedule(Runnable event, long delay) {
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}

	public void repeat(Runnable event, long initialDelay, long period) {
		scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
	}

	class LightOn implements Runnable {
		@Override
		public void run() {
			System.out.println("Turning on lights.");
			light = true;
		}
	}

	class LightOff implements Runnable {
		@Override
		public void run() {
			System.out.println("Turning off lights.");
			light = false;
		}
	}

	class WaterOn implements Runnable {
		@Override
		public void run() {
			System.out.println("Turning GreenHouse water on.");
			water = true;
		}
	}

	class WaterOff implements Runnable {
		@Override
		public void run() {
			System.out.println("Turning GreenHouse water off.");
			water = false;
		}
	}

	class ThermostatNight implements Runnable {
		@Override
		public void run() {
			System.out.println("Thermostat to Night setting.");
			thermostat = "Night";
		}
	}

	class ThermostatDay implements Runnable {
		@Override
		public void run() {
			System.out.println("Thermostat to Day setting.");
			thermostat = "Day";
		}
	}

	class Bell implements Runnable {
		@Override
		public void run() {
			System.out.println("Ding...");
		}
	}

	class Terminate implements Runnable {
		@Override
		public void run() {
			System.out.println("Terminating...");
			scheduler.shutdownNow();
			new Thread(){
				@Override
				public void run() {
					for (DataPoint data : datas) {
						System.out.println(data);
					}
				}
			}.start();
		}
	}

	static class DataPoint {

		final Calendar time;
		final float temperature;
		final float humidity;

		public DataPoint(Calendar time, float temperature, float humidity) {
			this.time = time;
			this.temperature = temperature;
			this.humidity = humidity;
		}

		@Override
		public String toString() {
			return String.format("temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
		}
	}

	private Calendar lastTime = Calendar.getInstance();

	{
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}

	private float lastTemp = 65.0f;
	private int tempDirection = +1;
	private float lastHumidity = 50.0f;
	private int humidityDirection = +1;
	private Random random = new Random();
	List<DataPoint> datas = Collections.synchronizedList(new ArrayList<>());

	class CollectionData implements Runnable {
		@Override
		public void run() {
			System.out.println("Collectioning Data");
			synchronized (GreenHouseScheduler.this) {
				lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
				if (random.nextInt(5) == 4)
					tempDirection = -tempDirection;
				lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());
				if (random.nextInt(5) == 4)
					humidityDirection = -humidityDirection;
				lastHumidity = lastHumidity + humidityDirection * random.nextFloat();
				datas.add(new DataPoint((Calendar) lastTime.clone(),lastTemp,lastHumidity));
			}
		}
	}

	public static void main(String[] args) {
		GreenHouseScheduler ghs = new GreenHouseScheduler();
		ghs.schedule(ghs.new Terminate(), 5000);
		ghs.repeat(ghs.new Bell(), 0, 1000);
		ghs.repeat(ghs.new ThermostatNight(), 0, 2000);
		ghs.repeat(ghs.new LightOn(), 0, 200);
		ghs.repeat(ghs.new LightOff(), 0, 400);
		ghs.repeat(ghs.new WaterOn(), 0, 600);
		ghs.repeat(ghs.new WaterOff(), 0, 800);
		ghs.repeat(ghs.new ThermostatDay(), 0, 1400);
		ghs.repeat(ghs.new CollectionData(), 500, 500);

	}
}
