package com.xhk.web.servlet.nio;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author xhk
 * @time 2018-12-19 13:14
 */
public class ZipCompress {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("src/temp/all.zip");
		CheckedOutputStream csum = new CheckedOutputStream(fos, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(csum);
		BufferedOutputStream bos = new BufferedOutputStream(zos);

		String[] strs = {"src/temp/data.txt","src/temp/out.txt"};

		for (String arg : strs) {
			System.out.println("Writing file " + arg);
			BufferedReader br = new BufferedReader(new FileReader(arg));
			zos.putNextEntry(new ZipEntry(arg));
			int i;
			while ((i = br.read()) != -1) {
				bos.write(i);
			}
			br.close();
			bos.flush();
		}
		bos.close();

//		System.out.println("Checksum " + csum.getChecksum().getValue());

		System.out.println("---------------------------");

		System.out.println("Read file");

		FileInputStream fis = new FileInputStream("src/temp/all.zip");
		CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());
		ZipInputStream zin = new ZipInputStream(cis);
		BufferedInputStream bis = new BufferedInputStream(zin);

		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			System.out.println("Read file " + ze.getName());
			int x;
			while ((x = bis.read()) != -1) {
				System.out.print((char)x);
			}
			System.out.println();
			System.out.println();
		}

		System.out.println("Checksum " + cis.getChecksum().getValue());

		ZipFile zf = new ZipFile("src/temp/all.zip");
		Enumeration<? extends ZipEntry> e = zf.entries();
		while (e.hasMoreElements()) {
			ZipEntry zipEntry = e.nextElement();
			System.out.println(zipEntry.getName());
		}

	}
}
