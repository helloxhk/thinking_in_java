package com.xhk.demo.nio;

import java.io.*;
import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-19 14:27
 */
public class Worm implements Serializable {

	private static Random random = new Random();

	Data[] datas = {
			new Data(random.nextInt(10)),
			new Data(random.nextInt(10)),
			new Data(random.nextInt(10)),
	};

	private Worm next;

	private char c;

	public Worm(int i, char x) {
		this.c = x;
		if (--i > 0)
			next = new Worm(i, (char) (x + 1));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(":");
		sb.append(c);
		sb.append("(");
		for (Data data : datas) {
			sb.append(data);
		}
		sb.append(")");
		if (next != null) {
			sb.append(next);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Worm w1 = new Worm(6, 'a');
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/temp/worm.temp"));
		out.writeObject("Worm storage");
		out.writeObject(w1);
		out.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/temp/worm.temp"));
		String str = (String)in.readObject();
		Worm w2 = (Worm) in.readObject();
		System.out.println(str);
		System.out.println(w2);

		System.out.println("-------------------------------------------------");

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject("Worm storage");
		oos.writeObject(w1);
		oos.flush();

		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
		str = (String)ois.readObject();
		w2 = (Worm) ois.readObject();
		System.out.println(str);
		System.out.println(w2);

	}
}

class Data implements Serializable {
	private int i;

	public Data(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return Integer.toString(i);
	}
}
