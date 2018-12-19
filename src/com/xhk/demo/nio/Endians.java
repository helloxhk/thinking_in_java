package com.xhk.demo.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author xhk
 * @time 2018-12-18 16:34
 */
public class Endians {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(12);
		bb.asCharBuffer().put("abcdef");
		bb.order(ByteOrder.BIG_ENDIAN);
		System.out.println(Arrays.toString(bb.array()));

		bb.rewind();
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.asCharBuffer().put("abcdef");
		System.out.println(Arrays.toString(bb.array()));
	}
}
