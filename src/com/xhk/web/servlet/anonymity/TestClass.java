package com.xhk.web.servlet.anonymity;

/**
 * @author xhk
 * @time 2018-11-27 13:26
 */
public class TestClass {

	static DestClass getDest(int x) {
		return new DestClass(){
			@Override
			public void print() {
				System.out.println(super.x);
			}
		};
	}

	public static void main(String[] args) {
		TestClass.getDest(12).print();
	}
}
