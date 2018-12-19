package com.xhk.demo.io;

import java.io.*;

/**
 * @author xhk
 * @time 2018-12-17 17:54
 */
public class BasicFileOutput {

	static String path = "src/com/xhk/web/servlet/io/BasicFileOutput.out";

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("src/com/xhk/web/servlet/io/BasicFileOutput.java")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
		String s;
		int lineCount = 1;
		while ((s = in.readLine()) != null) {
			out.println(lineCount++ + "    " + s);
		}
		out.close();
		in.close();
	}

}
