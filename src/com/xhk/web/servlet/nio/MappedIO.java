package com.xhk.web.servlet.nio;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

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
				System.out.println(String.format("耗时：%s",time));
			} catch (FileNotFoundException e) {
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
				for (int i = 0; i < size; i++) {
					dis.readInt();
				}
				dis.close();
			}
		},
		new Tester("Mapped Read") throws IOException{
			@Override
			public void test() {

			}
		},
		new Tester("Stream Read/Write") throws IOException{
			@Override
			public void test() {

			}
		},
		new Tester("Mapped Read/Write") throws IOException{
			@Override
			public void test() {

			}
		},
	};

	public static void main(String[] args) {
		for (Tester tester : testers) {
			tester.runTest();
		}
	}
}
