package com.xhk.demo.test;

/**
 * @author xhk
 * @time 2018-11-27 17:06
 */
public class A {
	U getU() {
		return new U() {
			@Override
			public void f1() {
				System.out.println("f1()");
			}

			@Override
			public void f2() {
				System.out.println("f2()");
			}

			@Override
			public void f3() {
				System.out.println("f3()");
			}
		};
	}
}
