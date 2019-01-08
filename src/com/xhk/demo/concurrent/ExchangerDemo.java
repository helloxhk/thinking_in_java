package com.xhk.demo.concurrent;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author xhk
 * @time 2019-01-07 14:46
 */
public class ExchangerDemo {

	static int size = 10;
	static int delay = 5;

	public static void main(String[] args) throws InterruptedException {
		Exchanger<List<Fat>> exchanger = new Exchanger<>();
		List<Fat>
				producer = new CopyOnWriteArrayList<>(),
				consumer = new CopyOnWriteArrayList<>();
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new ExchangerProducer<>(Fat.class, exchanger, producer));
		executor.execute(new ExchangerConsumer<>(exchanger, consumer));

		TimeUnit.SECONDS.sleep(delay);

		executor.shutdownNow();
	}
}

class ExchangerProducer<T> implements Runnable {

	private Exchanger<List<T>> exchanger;

	private List<T> list;

	private Class<T> clazz;

	public ExchangerProducer(Class<T> clazz, Exchanger<List<T>> exchanger, List<T> list) {
		this.exchanger = exchanger;
		this.list = list;
		this.clazz = clazz;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int i = 0; i < ExchangerDemo.size; i++) {
					list.add(clazz.newInstance());
				}
				list = exchanger.exchange(this.list);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ExchangerConsumer<T> implements Runnable {

	private Exchanger<List<T>> exchanger;

	private List<T> list;

	private volatile T value;

	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> list) {
		this.exchanger = exchanger;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				list = exchanger.exchange(list);
				for (T t : list) {
					value = t;
					System.out.println("Consume " + t);
					list.remove(t);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final value = " + value);
	}
}