package com.xhk.demo.nio;

import java.io.*;

/**
 * @author xhk
 * @time 2018-12-19 15:32
 */
public class SerializableDemo implements Serializable {

	private String a;

	private transient String b;

	public SerializableDemo(String a, String b) {
		this.a = "Not Transient : " + a;
		this.b = "Transient : " + b;
	}

	@Override
	public String toString() {
		return a + "\n" + b;
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		b = (String) ois.readObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeObject(b);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		SerializableDemo obj = new SerializableDemo("first", "second");
		System.out.println(obj);
		oos.writeObject(obj);

		System.out.println("--------------------------");

		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		obj = (SerializableDemo) ois.readObject();
		System.out.println(obj);
	}
}
