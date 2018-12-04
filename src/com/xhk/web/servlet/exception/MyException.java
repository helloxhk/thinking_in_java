package com.xhk.web.servlet.exception;

/**
 * @author xhk
 * @time 2018-12-04 9:33
 */
public class MyException extends Exception {

	private int x;

	public MyException() {}

	public MyException(String msg) {
		super(msg);
	}

	public MyException(String msg, int x) {
		super(msg);
		this.x = x;
	}

	public int val() {
		return x;
	}

	@Override
	public String getMessage() {
		return "Detail message : " + super.getMessage();
	}
}

class ExtraFeatures {

	public static void f() throws MyException {
//		throw new MyException();
	}

	public static void g() {
		try {
			throw new MyException("g()");
		} catch (MyException e) {
			for (StackTraceElement stackTraceElement : e.getStackTrace()) {
				System.out.println(stackTraceElement);
			}
			e.printStackTrace();
		}
	}

	public static void a() {
		g();
	}

	public static void b() {
		a();
	}

	public static void h() throws MyException {
		throw new MyException("h()",47);
	}

	public static void main(String[] args) {
//		try {
//			f();
//		} catch (MyException e) {
//			e.printStackTrace();
//		}
//		try {
//			f();
//		} catch (MyException e) {
//			e.printStackTrace(System.err);
//		}
//		try {
//			g();
//		} catch (MyException e) {
//			e.printStackTrace(System.err);
//		}
//		try {
//			h();
//		} catch (MyException e) {
//			e.printStackTrace(System.err);
//		}
//		g();
		b();
	}
}
