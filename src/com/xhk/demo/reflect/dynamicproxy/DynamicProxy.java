package com.xhk.demo.reflect.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xhk
 * @time 2018-12-10 10:19
 */
public class DynamicProxy implements InvocationHandler {

	private Interface1 real;

	public DynamicProxy(Interface1 real) {
		this.real = real;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass().getSimpleName());
		System.out.println(method.getName());
		System.out.println(Arrays.toString(args));
		return method.invoke(real,args);
	}
}
