package com.xhk.demo.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-03 14:53
 */
public class PipedIO {

	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(sender);
		executor.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		executor.shutdownNow();
	}
}

class Receiver implements Runnable {

	private PipedReader reader;

	public Receiver(Sender sender) throws IOException {
		this.reader = new PipedReader(sender.getWriter());
	}

	@Override
	public void run() {
		try {
			while (true) {
				char c = (char) reader.read();
				System.out.println(c);
			}
		} catch (IOException e) {
			System.out.println("Reader Exception");
		}
	}
}

class Sender implements Runnable {

	private Random random = new Random();

	private PipedWriter writer = new PipedWriter();

	public Sender() {
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (char c = 'A'; c <= 'z' ; c++) {
					writer.write(c);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
				}
			}
		} catch (IOException e) {
			System.out.println("Sender write Exception");
		} catch (InterruptedException e) {
			System.out.println("Sender innterrupt Exception");
		}
	}

	public PipedWriter getWriter() {
		return writer;
	}
}
