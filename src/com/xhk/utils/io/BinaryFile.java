package com.xhk.utils.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xhk
 * @time 2018-12-18 11:14
 */
public class BinaryFile {

	public static byte[] read(File file) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		try {
			byte[] bys;
			bys = new byte[in.available()];
			in.read(bys, 0, in.available());
			return bys;
		} finally {
			in.close();
		}
	}

	public static byte[] read(String fileName) throws IOException {
		return read(new File(fileName));
	}
}
