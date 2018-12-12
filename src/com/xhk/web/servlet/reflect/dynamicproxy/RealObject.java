package com.xhk.web.servlet.reflect.dynamicproxy;

/**
 * @author xhk
 * @time 2018-12-10 10:17
 */
public class RealObject implements Interface1 {

	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void somethingElse(String string) {
		System.out.println("somethingElse " + string);
	}
}
