package com.xhk.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author xhk
 * @time 2018-12-25 16:57
 */
public class UncaughtExceptionDemo {

	public static void main(String[] args) {
		/*ExecutorService executor = Executors.newSingleThreadExecutor(new ExceptionHandlerThreadFactory());
		executor.execute(new ExceptionThread2());*/
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		new Thread(new ExceptionThread2()).start();
	}
}

/**
 * 未捕获异常处理器
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}

/**
 * 异常线程
 */
class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("run by " + thread);
		System.out.println("exceptionHandler : " + thread.getUncaughtExceptionHandler());
		throw new RuntimeException("haha...");
	}
}

/**
 * 线程工厂
 * 		为产生的线程设置一些公共值、
 * 		例如
 * 			设置优先级
 * 			设置后台线程
 */
class ExceptionHandlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		System.out.println("create " + thread);
		thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		return thread;
	}
}
