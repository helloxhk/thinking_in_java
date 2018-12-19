package com.xhk.demo.nio;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author xhk
 * @time 2018-12-19 11:16
 */
public class GzipCompress {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/temp/io.temp"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream("src/temp/io.gzip"))));
		char[] buff = new char[1024];
		int i;
		while ((i = in.read(buff, 0, buff.length)) != -1) {
			out.write(buff, 0, i);
		}
		in.close();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("src/temp/io.gzip"))));

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/temp/unzip.temp"));

		String str;
		while ((str = reader.readLine()) != null)
			bos.write(str.getBytes());

		System.out.println(111111);
	}
}
