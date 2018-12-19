package com.xhk.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xhk
 * @time 2018-12-18 17:54
 */
public class largeMappedFiles {

	private static final int length = 0x8000000;

	public static void main(String[] args) throws IOException {
		MappedByteBuffer mbb = new RandomAccessFile("src/temp/large.txt", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for (int i = 0; i < length; i++) {
			mbb.put((byte) 'a');
		}

		System.out.println("Finish Writing");

		for (int i = length / 2; i < length / 2 + 6; i++) {
			System.out.println(mbb.get(i));
		}
	}
}
