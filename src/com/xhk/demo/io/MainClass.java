package com.xhk.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author xhk
 * @time 2018-12-18 13:05
 */
public class MainClass {

	public static void main(String[] args) {
		test2();
	}

	static void test1() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while ((str = in.readLine()) != null && str.length() > 0)
			System.out.println(str);
	}

	static void test2() {
		// PrintWriter(x,y) 第二个参数为true 开启自动清空（flush）功能
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("你好，你好。");
	}
}
