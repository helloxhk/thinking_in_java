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

		System.out.println("----------------------");

		LinkedHashMap<Integer, String> lh = new LinkedHashMap<Integer, String>(16 , 0.75f, true){{
			put(0, "x");
			put(1, "x");
			put(2, "x");
			put(3, "x");
			put(4, "x");
			put(5, "x");
			put(6, "x");
			put(7, "x");
			put(8, "x");
			put(9, "x");
		}};
		System.out.println(lh.get(null)); // 0 1 2 3 4 5 6 7 8 9
		for (int i = 0; i < 6; i++) {
			lh.get(i);
		}
		System.out.println(lh); // 6 7 8 9 0 1 2 3 4 5
		lh.get(0);
		System.out.println(lh); // 6 7 8 9 1 2 3 4 5 0

		System.out.println("----------------------");

		HashMap<HashObj,Object> hm = new HashMap(){{
			put(new HashObj(1), 1);
			put(new HashObj(1), 1);
			put(new HashObj(1), 1);
			put(new HashObj(1), 1);

		}};
		System.out.println(hm.size());

		System.out.println("----------------------");
		HashMap<Integer,Integer> hashMap = new HashMap(){{
			put(1, null);
			put(2, null);
			put(3, null);
			put(4, null);
			put(5, null);
		}};
		System.out.println(hashMap);

		System.out.println("----------------------");

		Iterator<Integer> iterator = list.iterator();
		list.add(1);
		try {
			Integer next = iterator.next();
		} catch (ConcurrentModificationException e) {
			// 获取迭代器后，再堆容器进行操作，将报出并发修改异常
			// e.printStackTrace();
			System.err.println("并发修改");
		}
	}
}

class HashObj {
	private int i;

	public HashObj(int i) {
		this.i = i;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		HashObj hashObj = (HashObj) object;
		return i == hashObj.i;
	}

	@Override
	public int hashCode() {
		return i;
	}
}
