package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xhk
 * @time 2018-12-25 16:46
 */
public class NativeExceptionHanding {

}

class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException("aaaaaaaaaaaaaaaaaaaa");
	}

	public static void main(String[] args) {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("sss");
		}
	}
}
