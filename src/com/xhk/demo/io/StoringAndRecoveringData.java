package com.xhk.demo.io;

import java.io.*;

/**
 * @author xhk
 * @time 2018-12-17 18:20
 */
public class StoringAndRecoveringData {

	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
		out.writeDouble(3.1415925D);
		out.writeUTF("张三");
		out.writeDouble(1.11111D);
		out.writeUTF("李四");
		out.close();

		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		in.close();
	}
}
