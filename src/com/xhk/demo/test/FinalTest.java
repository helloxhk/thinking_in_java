package com.xhk.demo.test;

import java.util.Random;

/**
 * @author xhk
 * @time 2018-11-16 14:31
 */
public class FinalTest {

	public final int i1 = new Random().nextInt();
	public final static int i2 = new Random().nextInt();

	public static void main(String[] args) {
		testOverload(1,2l);
		testOverload(2l,1);
	}

	public static void testOverload(int i,long l) {
		System.out.println("int " + i + " long " + l);
	}

	public static void testOverload(long l,int i) {
		System.out.println("long " + l + " int " + i);
	}

}
