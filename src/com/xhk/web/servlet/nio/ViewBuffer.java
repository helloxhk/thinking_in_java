package com.xhk.web.servlet.nio;

import java.nio.*;

/**
 * @author xhk
 * @time 2018-12-18 16:11
 */
public class ViewBuffer {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
		bb.rewind();
		System.out.println("ByteBuffer");
		while (bb.hasRemaining())
			System.out.print(bb.position() + " -> " + bb.get() + "\t");
		System.out.println();

		CharBuffer cb = ((ByteBuffer) bb.rewind()).asCharBuffer();
		System.out.println("CharBuffer");
		while (cb.hasRemaining())
			System.out.print(cb.position() + " -> " + cb.get() + "\t");
		System.out.println();

		ShortBuffer sb = ((ByteBuffer) bb.rewind()).asShortBuffer();
		System.out.println("ShortBuffer");
		while (sb.hasRemaining())
			System.out.print(sb.position() + " -> " + sb.get() + "\t");
		System.out.println();

		IntBuffer ib = ((ByteBuffer) bb.rewind()).asIntBuffer();
		System.out.println("IntBuffer");
		while (ib.hasRemaining())
			System.out.print(ib.position() + " -> " + ib.get() + "\t");
		System.out.println();

		FloatBuffer fb = ((ByteBuffer) bb.rewind()).asFloatBuffer();
		System.out.println("FloatBuffer");
		while (fb.hasRemaining())
			System.out.print(fb.position() + " -> " + fb.get() + "\t");
		System.out.println();

		LongBuffer lb = ((ByteBuffer) bb.rewind()).asLongBuffer();
		System.out.println("LongBuffer");
		while (lb.hasRemaining())
			System.out.print(lb.position() + " -> " + lb.get() + "\t");
		System.out.println();

		DoubleBuffer db = ((ByteBuffer) bb.rewind()).asDoubleBuffer();
		System.out.println("DoubleBuffer");
		while (db.hasRemaining())
			System.out.print(db.position() + " -> " + db.get() + "\t");
		System.out.println();
	}
}
