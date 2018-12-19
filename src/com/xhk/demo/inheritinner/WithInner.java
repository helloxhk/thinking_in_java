package com.xhk.demo.inheritinner;

/**
 * @author xhk
 * @time 2018-11-28 16:05
 */
public class WithInner {

	private String string = "wwwwwww";

	public void setString(String string) {
		this.string = string;
	}

	public WithInner() {
		System.out.println("WihtInner constructor");
	}

	class Inner {
		public Inner() {
			System.out.println("Inner constructor");
		}

		void print() {
			System.out.println(string);
		}
	}
}