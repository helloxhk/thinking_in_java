package com.xhk.demo.nio;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-19 15:17
 */
public class TransientDemo implements Serializable {

	private Date date = new Date();

	private String username;

	private transient String password;

	public TransientDemo(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "username : " + username + "\n" +
				"password : " + password + "\n" +
				"date : " + date;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/temp/transient.temp"));
		TransientDemo transientDemo1 = new TransientDemo("username", "password");
		System.out.println(transientDemo1);
		oos.writeObject(transientDemo1);

		TimeUnit.MILLISECONDS.sleep(1000);
		System.out.println(new Date());

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/temp/transient.temp"));
		TransientDemo transientDemo = (TransientDemo) ois.readObject();
		System.out.println(transientDemo);
	}
}
