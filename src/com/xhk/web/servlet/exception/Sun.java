package com.xhk.web.servlet.exception;

import java.util.Arrays;
import java.util.Formatter;

/**
 * @author xhk
 * @time 2018-12-04 13:44
 */
public class Sun extends Father {

	public Sun() throws Exception{

	}

	@Override
	public void f(){
//		super.x;
//		super.f();
	}

	public static void main(String[] args) {
//		String s = "string";
//		System.out.println(s);
//		System.out.println(Arrays.toString(s.getBytes()));
//		int x = 10;
//		double y = 1.123;
//		System.out.printf("info [%d , %f]\r\n", x, y);
//		System.out.format("info [%d , %f]\r\n", x, y);
		Formatter formatter = new Formatter(System.out);
//		formatter.format("info [%d , %f]\r\n", x, y);
//		formatter.format("%-10s %5s %5s\n","name","age","score");
//		formatter.format("%-10s %5s %5s\n","----","---","-----");
//		formatter.format("%-10s %5d %5.2f","zhangsan",11,98.5);
		formatter.format("%x", 12);
		formatter.format("%h", 12);
		"".split("");
	}
}
