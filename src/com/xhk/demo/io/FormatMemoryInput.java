package com.xhk.demo.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author xhk
 * @time 2018-12-17 17:40
 */
public class FormatMemoryInput {

	public static void main(String[] args) {
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("src/com/xhk/web/servlet/io/FormatMemoryInput.java").getBytes()));
			while (true)
				System.out.println((char)in.readByte());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
