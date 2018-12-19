package com.xhk.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author xhk
 * @time 2018-12-18 14:52
 */
public class BufferToText {

	static final int SIZE = 1024;

	public static void main(String[] args) throws IOException {

		FileChannel fc = new FileOutputStream("src/temp/data.txt").getChannel();
		fc.write(ByteBuffer.wrap("第一行".getBytes()));
		fc.close();

		fc = new FileInputStream("src/temp/data.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(SIZE);
		fc.read(buff);
		fc.close();
		buff.flip();
		System.out.println(Charset.forName(System.getProperty("file.encoding")).decode(buff));

		System.out.println("**********************");

		fc = new FileOutputStream("src/temp/data.txt").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("第二行".getBytes("UTF-16BE")));
		fc.close();

		fc = new FileInputStream("src/temp/data.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());

		System.out.println("**********************");

		fc = new FileOutputStream("src/temp/data.txt").getChannel();
		buff = ByteBuffer.allocate(24);
		buff.asCharBuffer().put("张三 李四是是是ss");
		fc.write(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());

//		while (buff.hasRemaining())
//			System.out.print((char)buff.get());

		ByteBuffer buf = ByteBuffer.allocate(SIZE);
		buf.asCharBuffer().put('涨');
		char c;
		while ((c = buf.getChar()) != 0)
			System.out.println(c);
	}
}
