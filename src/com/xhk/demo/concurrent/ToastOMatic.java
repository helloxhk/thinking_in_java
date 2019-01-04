package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-03 14:13
 */
public class ToastOMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue
				toastQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		Toaster toaster = new Toaster(toastQueue);
		Butterer butterer = new Butterer(toastQueue, butteredQueue);
		Jammer jammer = new Jammer(butteredQueue, finishedQueue);
		Eater eater = new Eater(finishedQueue);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(toaster);
		executor.execute(butterer);
		executor.execute(jammer);
		executor.execute(eater);
		TimeUnit.SECONDS.sleep(5);
		executor.shutdownNow();
	}
}

class Eater implements Runnable {

	private ToastQueue finishedQueue;

	private int count = 0;

	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast finished = finishedQueue.take();
				if (finished.getStatus() != Toast.Status.JAMMED || finished.getId() != count++) {
					System.out.println(">>>> Error : " + finished);
					System.exit(1);
				} else {
					System.out.println("Eated " + finished);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Eater interrupted.");
		}
		System.out.println("Eater off");
	}
}

class Jammer implements Runnable {

	private ToastQueue butteredQueue;
	private ToastQueue finishedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast butterd = butteredQueue.take();
				butterd.jam();
				System.out.println(butterd);
				finishedQueue.put(butterd);
			}
		} catch (InterruptedException e) {
			System.out.println("Jammer interrupted.");
		}
		System.out.println("Jammer off");
	}
}

class Butterer implements Runnable {

	private ToastQueue toastQueue;
	private ToastQueue butteredQueue;

	public Butterer(ToastQueue toastQueue, ToastQueue butteredQueue) {
		this.toastQueue = toastQueue;
		this.butteredQueue = butteredQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = toastQueue.take();
				toast.butter();
				System.out.println(toast);
				butteredQueue.put(toast);
			}
		} catch (InterruptedException e) {
			System.out.println("Butterer interrupted.");
		}
		System.out.println("Butterer off");
	}
}

class Toaster implements Runnable {

	private ToastQueue toasts;

	private int count = 0;

	public Toaster(ToastQueue toasts) {
		this.toasts = toasts;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				Toast toast = new Toast(count++);
				System.out.println(toast);
				toasts.put(toast);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted.");
		}
		System.out.println("Toaster off");
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {

}

class Toast {

	public enum Status {
		DRY,BUTTERED, JAMMED
	}

	private Status status = Status.DRY;

	private final int id;

	public Toast(int id) {
		this.id = id;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Toast " + id + " - " + status;
	}
}
