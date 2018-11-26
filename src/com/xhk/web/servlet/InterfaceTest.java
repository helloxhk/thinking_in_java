package com.xhk.web.servlet;

public interface InterfaceTest {

	int i = 1;


	public default void test() {
		System.out.println("default");
	}

}
