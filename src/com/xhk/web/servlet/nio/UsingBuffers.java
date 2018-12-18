package com.xhk.web.servlet.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author xhk
 * @time 2018-12-18 16:57
 */
public class UsingBuffers {

	public static void main(String[] args) {
		char[] chars = "UsingBuffers".toCharArray();
		ByteBuffer bb = ByteBuffer.allocate(chars.length * 2);
		CharBuffer cb = bb.asCharBuffer().put(chars);
		System.out.println(cb.rewind());

		swap(cb);
		System.out.println(cb.rewind());

		swap(cb);
		System.out.println(cb.rewind());

	}

	static void swap(CharBuffer buffer) {
		while (buffer.hasRemaining()) {
			buffer.mark(); // 标记 position
			char c1 = buffer.get();
			char c2 = buffer.get();
			buffer.reset(); // 重回标记位置
			buffer.put(c2).put(c1);
		}
	}
}
