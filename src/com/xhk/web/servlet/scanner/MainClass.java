package com.xhk.web.servlet.scanner;

import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-06 16:49
 */
public class MainClass {
	public static void main(String[] args) {
		/*Scanner scanner = new Scanner("1,2,3,4,5,6,7");
		scanner.useDelimiter("\\s*,\\s*");
		while (scanner.hasNextInt()) {
			System.out.println(scanner.nextInt());
		}
		System.out.println(scanner.delimiter());*/
		/*String data = "192.168.1.117@2018/12/06\n" +
				"192.168.1.118@2018/12/07";
		String regex = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{4}/\\d{2}/\\d{2})";
		Scanner scanner = new Scanner(data);
		while (scanner.hasNext(regex)) {
			scanner.next(regex);
			MatchResult match = scanner.match();
			String ip = match.group(1);
			String date = match.group(2);
			System.out.println("id : " + ip + " date : " + date);
		}*/
		/*System.out.println(String.class.getCanonicalName());
		System.out.println(String.class.getName());
		System.out.println();*/
//		printClassName(Chess.class);
//		System.out.println(Demo.finalInt2);
		System.out.println(Class1.class.isAssignableFrom(Inter2.class));
	}

	public static void printClassName(Class clazz) {
		if (clazz.getSuperclass() != null)
			printClassName(clazz.getSuperclass());
		System.out.println(clazz.getSigners());
		System.out.println(clazz.getSimpleName());
	}
}

class Demo {
	static final int finalInt = 10;
	static final int finalInt2 = new Random().nextInt();
	static int staticInt = 111;
	static {
		System.out.println("xxxxxxxxxxxxxxxxxxx");
	}
}
