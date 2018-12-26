package com.xhk.demo.concurrent;

import java.io.IOException;

/**
 * @author xhk
 * @time 2018-12-25 16:35
 */
public class ResponsiveUI extends Thread {

	private static volatile double d;

	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		while(true)
			d += (Math.PI + Math.E) / d;
	}

	public static void main(String[] args) throws IOException {
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);
	}
}
