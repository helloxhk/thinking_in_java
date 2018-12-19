package com.xhk.demo.nio;

import java.io.*;

/**
 * 使用 Externalizable 必须有默认的构造器
 * 		否则报错：no valid constructor
 * @author xhk
 * @time 2018-12-19 15:00
 */
public class Blip implements Externalizable {

	private int i;
	private String s;

	public Blip() {
		System.out.println("invoke Blip's constructor...");
	}

	public Blip(int i, String s) {
		this.i = i;
		this.s = s;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("invoke Blip's writeExternal...");
		out.writeInt(i);
		out.writeObject(s);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("invoke Blip's readExternal...");
		i = in.readInt();
		s = (String) in.readObject();
	}

	@Override
	public String toString() {
		return s + i;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Blip blip = new Blip(35, "A Strnig");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/temp/blip.temp"));
		oos.writeObject(blip);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/temp/blip.temp"));
		blip = (Blip) ois.readObject();
		System.out.println(blip);
	}
}
