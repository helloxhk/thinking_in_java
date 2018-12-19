package com.xhk.web.servlet.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2018-12-19 10:07
 */
public class FileLocking {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileOutputStream fos = new FileOutputStream("src/temp/io.temp");
		FileChannel fc = fos.getChannel();
		// 对文件 指定部分加锁
		FileLock fl = fc.tryLock(0, fc.size(), false);
		if (fl != null) {
			System.out.println("File locked");
			System.out.println("是否共享锁：" + fl.isShared());
			// 睡眠 100 毫秒
			TimeUnit.MILLISECONDS.sleep(100);
			// 释放锁
			fl.release();
			System.out.println("File released");
		}
		// 关闭资源
		fos.close();
	}
}
