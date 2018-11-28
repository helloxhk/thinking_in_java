package com.xhk.web.servlet.test;

/**
 * @author xhk
 * @time 2018-11-27 17:07
 */
public class B {

	int index = 0;

	U[] us = new U[10];

	void add(U u) {
		if (index < us.length)
			us[index++] = u;
	}

	void clear() {
		us = new U[10];
	}

	void iterator() {
		for (U u : us) {
			u.f1();
			u.f2();
			u.f3();
		}
	}

	public static void main(String[] args) {
		B b = new B();
		for (int i = 0; i < 10; i++) {
			b.add(new A().getU());
		}
		b.iterator();
	}
}
