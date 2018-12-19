package com.xhk.demo.interfaces;

/**
 * @author xhk
 * @time 2018-11-26 14:23
 */
public class A {

	interface B {
		void f();
	}

	public class BImpl implements B {
		@Override
		public void f() {

		}
	}

	public class BImpl2 implements B {
		@Override
		public void f() {

		}
	}

	public interface C {
		void f();
	}

	class CImpl implements C {
		@Override
		public void f() {

		}
	}

	private class CImpl2 implements C {
		@Override
		public void f() {

		}
	}

	private interface D {
		int i = 1;
		void f();
	}

	private class DImple implements D {
		@Override
		public void f() {

		}
	}

	public class DImpl2 implements D {
		@Override
		public void f() {

		}
	}

	public D getD() {
		return new DImpl2();
	}

	private D dRef;

	public void receiveD(D d) {
		this.dRef = d;
		d.f();
	}
}
