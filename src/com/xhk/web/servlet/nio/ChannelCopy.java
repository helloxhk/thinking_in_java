package com.xhk.web.servlet.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xhk
 * @time 2018-12-18 14:29
 */
public class ChannelCopy {

	public static void main(String[] args) throws IOException {
//		copy("src/temp/text.txt", "src/temp/target.txt");
		copy2("src/temp/text.txt", "src/temp/target2.txt");
	}

	public static void copy(String source, String target) throws IOException {
		FileChannel
				in = new FileInputStream(source).getChannel(),
				out = new FileOutputStream(target).getChannel();
		ByteBuffer buff = ByteBuffer.allocate(1024);

		while (in.read(buff) != -1) {
			buff.flip(); // 准备写
			out.write(buff);
			buff.clear(); // 准备读
		}

		in.close();
		out.close();
	}

	public static void copy2(String source, String target) throws IOException {
		FileChannel
				in = new FileInputStream(source).getChannel(),
				out = new FileOutputStream(target).getChannel();
//		in.transferTo(0, in.size(), out);
		out.transferFrom(in, 0, in.size());
		in.close();
		out.close();
	}
}
