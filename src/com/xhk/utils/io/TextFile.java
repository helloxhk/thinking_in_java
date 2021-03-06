package com.xhk.utils.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author xhk
 * @time 2018-12-18 10:25
 */
public class TextFile extends ArrayList<String> {

	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.write(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		if ("".equals(get(0)))
			remove(0);
	}

	public TextFile(String fileName) {
		this(fileName, "\n");
	}

	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for (String s : this) {
					out.println(s);
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		String info = read("src/com/xhk/web/servlet/io/TextFile.java");
		write("src/temp/text.txt",info);
		TextFile text = new TextFile("src/temp/text.txt");
		text.write("src/temp/text2.txt");
		TreeSet<String> words = new TreeSet<>(new TextFile("src/com/xhk/web/servlet/io/TextFile.java","\\W+"));
		System.out.println(words.headSet("a"));
	}
}
