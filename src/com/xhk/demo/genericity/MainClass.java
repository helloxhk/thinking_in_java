package com.xhk.demo.genericity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xhk
 * @time 2018-12-11 14:31
 */
public class MainClass {

	public static void main(String[] args) {

		/*List list1 = null;
		List<Long> list2 = null;
		List<?> list3 = null;
		List<? extends Long> list4 = null;

		Long l = 1L;

		test1(list1,l); // 1
		test1(list2,l); // 1
		test1(list3,l); // 1
		test1(list4,l); // 1

		test2(list1,l); // 1
		test2(list2,l); // 1
		test2(list3,l); // 1
		test2(list4,l); // 1

		test3(list1); // 1
		test3(list2); // 1
		test3(list3); // 1
		test3(list4); // 1

		test4(list1,l); // 1
		test4(list2,l); // 1
//		test4(list3,l); // 0 不带泛型 说明任何Object都可以放，带了泛型 ? 说明能放任何 Object 中的一种
//		test4(list4,l); // 0

		test5(list1,l); // 1
		test5(list2,l); // 1
		test5(list3,l); // 0
		test5(list4,l); // 1

		test6(list1,l); // 1
		test6(list2,l); // 1
//		test6(list3,l); // 0
//		test6(list4,l); // 0*/

		List<Number> numbers = Collections.checkedList(new ArrayList<Number>(), Number.class);
		numbers.add(1);
		numbers.add(1L);
		numbers.add(1.5);
		System.out.println(numbers);
	}

	static void test1(List list, Object object) {

	}

	static void test2(List<?> list, Object object) {
		Object o = list.get(0);
	}

	static <T> void test3(List<T> list) {
		T t = list.get(0);
	}

	static <T> void test4(List<T> list, T t) {

	}

	static <T> void test5(List<? extends T> list, T t) {
		T t1 = list.get(0);
	}

	static <T> void test6(List<? super T> list, T t) {
		Object object = list.get(0);
	}

}