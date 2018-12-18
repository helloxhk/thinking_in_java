package com.xhk.web.servlet.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author xhk
 * @time 2018-12-18 9:50
 */
public class RandomAccessFileTest {

	static String filePath = "src/com/xhk/web/servlet/io/random.txt";

	static void display() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
		for (int i = 0; i < 7; i++) {
			System.out.println(raf.readDouble());
		}
		raf.close();
	}

	public static void main(String[] args) throws IOException {
		String filePath = "src/com/xhk/web/servlet/io/random.txt";
		RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
		for (int i = 0; i < 7; i++) {
			raf.writeDouble(i * 3.14);
		}
		raf.close();
		display();

		System.out.println();

		RandomAccessFile raf2 = new RandomAccessFile(filePath, "rw");
		raf2.seek(5 * 8); // 找到xxx位置
		raf2.writeDouble(1.11111D);
		raf2.close();
		display();
	}
}
