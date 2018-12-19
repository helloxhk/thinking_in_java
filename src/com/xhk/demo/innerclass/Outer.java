package com.xhk.demo.innerclass;

import com.xhk.demo.interfaces.Content;

/**
 * @author xhk
 * @time 2018-11-27 10:00
 */
public class Outer {

	private String outerString;

	public Outer(String outerString) {
		this.outerString = outerString;
	}

	Inner inner = new Inner(234);


	private class Inner implements Content {

		private int i;

		public Inner(int i) {
			this.i = i;
		}

		public String toString() {
			return Outer.this.outerString;
		}

		private void print() {
			System.out.println("inner print()");
		}

		@Override
		public int value() {
			return i;
		}
	}

	public Content getInner() {
		return new Inner(1);
	}

	int getInt() {
		inner.print();
		return inner.i;
	}

	public static void main(String[] args) {
//		Inner inner = new Outer("Outer").new Inner();
//		System.out.println(inner);
		System.out.println(new Outer("outer").getInt());
	}
}
