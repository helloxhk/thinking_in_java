package com.xhk.web.servlet.container;

import java.util.*;

/**
 * @author xhk
 * @time 2018-12-12 16:55
 */
public class MainClass {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Collections.nCopies(5, 88));
		System.out.println(list);
		Collections.fill(list,new Integer("99"));
		System.out.println(list);
		new ArrayList<>(new ArrayList<>());
		char c = 'A';
		System.out.println(++c);

		System.out.println("----------------------");

		List<Integer> integers = new ArrayList<Integer>(){{
			add(1);
			add(1);
			add(1);
		}};
		System.out.println(integers.size());
		integers.clear();
		System.out.println(integers.size());
		Collection cc = new ArrayList();
		cc.add(1);

		System.out.println("----------------------");

		System.out.println(('A' > 'B'));
	}
}
