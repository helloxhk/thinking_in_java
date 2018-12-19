package com.xhk.demo.io;

import java.io.*;

/**
 * @author xhk
 * @time 2018-12-18 13:16
 */
public class Redirecting {
	public static void main(String[] args) throws IOException {
		PrintStream console = System.out;
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/temp/text.txt"));
		PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("src/temp/out.txt")));
		System.setErr(ps);
		System.setOut(ps);
		System.setIn(bis);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while ((str = br.readLine()) != null)
			System.out.println(str);
		ps.close();
		System.setOut(console);
	}
}
