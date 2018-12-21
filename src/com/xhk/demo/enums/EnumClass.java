package com.xhk.demo.enums;

/**
 * @author xhk
 * @time 2018-12-20 9:33
 */
public class EnumClass {

	public static void main(String[] args) {
		for (Shrubbery shrubbery : Shrubbery.values()) {
			System.out.println(shrubbery.name());
			System.out.println(shrubbery);
			System.out.println(shrubbery.getDeclaringClass());
			System.out.println(shrubbery.ordinal());
			System.out.println(shrubbery == Shrubbery.CRAWLING);
			System.out.println(shrubbery.compareTo(Shrubbery.CRAWLING));
			System.out.println(shrubbery.equals(Shrubbery.CRAWLING));
		}

		for (String str : "HAVING CRAWLING GROUND".split(" ")) {
			Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, str);
			System.out.println(shrubbery);
		}
	}
}

enum Shrubbery {
	GROUND, CRAWLING, HAVING
}
