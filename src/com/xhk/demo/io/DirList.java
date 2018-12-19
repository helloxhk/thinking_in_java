package com.xhk.demo.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author xhk
 * @time 2018-12-17 13:24
 */
public class DirList {

	public static void main(String[] args) {
		File file = new File("src/com/xhk/web/servlet");
//		System.out.println(file);
		String[] list = file.list();
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
		System.out.println(list.length);
		for (String s : list) {
			System.out.println(s);
		}
	}
}

class DirFilter implements FilenameFilter {

	private Pattern pattern;

	public DirFilter(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
}