package com.xhk.web.servlet.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author xhk
 * @time 2018-12-19 10:27
 */
public class LockingMappedFiles {
	// 128M
	private static final int SIZE = 0x8000000;

	private static FileChannel fc;

	public static void main(String[] args) throws IOException{
		fc = new RandomAccessFile("src/temp/io.temp", "rw").getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, SIZE);
		for (int i = 0; i < SIZE; i++) {
			mbb.put((byte) 'x');
		}
		new LockAndModify(mbb, 0, SIZE / 3);
		new LockAndModify(mbb, SIZE / 2, SIZE / 2 + SIZE / 4);
	}

	static class LockAndModify extends Thread {

		private ByteBuffer buffer;
		private int start, end;

		public LockAndModify(ByteBuffer mbb, int start, int end) {
			this.start = start;
			this.end = end;
			mbb.limit(end);
			mbb.position(start);
			buffer = mbb.slice();
			start();
		}

		@Override
		public void run() {
			try {
				FileLock lock = fc.lock(start, end, false);
				System.out.println(String.format("Lock start %s to %s", start, end));
				while (buffer.position() < buffer.limit() - 1)
					buffer.put((byte) (buffer.get() - 1));
				lock.release();
				System.out.println(String.format("Lock release %s to %s", start, end));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
