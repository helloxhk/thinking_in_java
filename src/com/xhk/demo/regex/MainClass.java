package com.xhk.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xhk
 * @time 2018-12-06 13:06
 */
public class MainClass {

	public static void main(String[] args) {
//		String str = "Java now has regular expressions";
		String str = "Arline ate eight apples and one orange while Anita hadn't any";
//		String str = "abcdef";
//		String regex = "(?i)((^[aeiou])|(\\s+[aeiou])\\w+?[aeiou]\\b)";
		String regex = "[aeiou]";
//		String regex = "^Java"; // true
//		String regex = "\\Breg.*"; // false
//		String regex = "n.w\\s+h(a|i)s"; // true
//		String regex = "s?"; // true
//		String regex = "s*"; // true
//		String regex = "s+"; // true
//		String regex = "s{4}"; // false
//		String regex = "s{1}"; // true
//		String regex = "s{0,3}"; // true
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
//			System.out.println(matcher.group());
//			System.out.println(matcher.start());
//			System.out.println(matcher.end());
//			System.out.println();
			matcher.appendReplacement(sb, matcher.group().toUpperCase());
		}
		matcher.appendTail(sb);
		System.out.println(sb);
//		System.out.println(matcher.matches());
//		System.out.println(matcher.lookingAt());
//		System.out.println(matcher.find(0));
	}
}
