package com.xhk.web.servlet.nio;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

/**
 * @author xhk
 * @time 2018-12-18 18:15
 */
public class MappedIO {

	private static final int size = 4000000;
	private static final int buffSize = 200000;

	private abstract static class Tester {
		private String name;

		public Tester(String name) {
			this.name = name;
		}

		public void runTest() {
			try {
				System.out.println(name + ":");
				long start = System.nanoTime();
				test();
				long time = System.nanoTime() - start;
				System.out.println(String.format("耗时：%s", time));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public abstract void test() throws IOException;
	}

	private static Tester[] testers = {
			new Tester("Stream Write") {
				@Override
				public void test() throws IOException {
					DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/temp/io.temp")));
					for (int i = 0; i < size; i++) {
						dos.writeInt(i);
					}
					dos.close();
				}
			},
			new Tester("Mapped Write") {
				@Override
				public void test() throws IOException {
					FileChannel fc = new RandomAccessFile("src/temp/io.temp", "rw").getChannel();
					IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
					for (int i = 0; i < size; i++) {
						ib.put(i);
					}
					fc.close();
				}
			},
			new Tester("Stream Read") {
				@Override
				public void test() throws IOException {
					DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("src/temp/io.temp")));
					for (int i = 0; i < dis.available(); i++) {
						dis.readInt();
					}
					dis.close();
				}
			},
			new Tester("Mapped Read") {
				@Override
				public void test() throws IOException {
					IntBuffer ib = new RandomAccessFile("src/temp/io.temp", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, size).asIntBuffer();
					while (ib.hasRemaining())
						ib.get();
				}
			},
			new Tester("Stream Read/Write") {
				@Override
				public void test() throws IOException {
					RandomAccessFile raf = new RandomAccessFile("src/temp/io.temp", "rw");
					raf.writeInt(1);
					for (int i = 0; i < buffSize; i++) {
						raf.seek(raf.length() - 4);
						raf.writeInt(raf.readInt());
					}
				}
			},
			new Tester("Mapped Read/Write") {
				@Override
				public void test() throws IOException {
					IntBuffer ib = new RandomAccessFile("src/temp/io.temp", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, size).asIntBuffer();
					/**
					 * java.io.FileNotFoundException: src\temp\io.temp (请求的操作无法在使用用户映射区域打开的文件上执行。)
					 */
//					IntBuffer ib = new FileOutputStream("src/temp/io.temp").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, size).asIntBuffer();
					ib.put(1);
					for (int i = 1; i < buffSize; i++) {
						ib.put(ib.get(i - 1));
					}
				}
			},
	};

	public static void main(String[] args) {
		for (Tester tester : testers) {
			tester.runTest();
		}
	}
}
