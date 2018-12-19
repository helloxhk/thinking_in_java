package com.xhk.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xhk
 * @time 2018-12-18 14:17
 */
public class GetChannel {

	private static final int SIZE = 1024;

	public static void main(String[] args) throws IOException {
		FileChannel fc = new FileOutputStream("src/temp/text.txt").getChannel();
		fc.write(ByteBuffer.wrap("first out".getBytes("UTF-8")));
		fc.close();

		fc = new RandomAccessFile("src/temp/text.txt","rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("\nsecond out".getBytes("UTF-8")));
		fc.close();

		fc = new FileInputStream("src/temp/text.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(SIZE);
		fc.read(buff);
		buff.flip();
		while (buff.hasRemaining())
			System.out.print((char)buff.get());
		fc.close();
	}
}
