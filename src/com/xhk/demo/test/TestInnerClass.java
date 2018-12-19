package com.xhk.demo.test;

import com.xhk.demo.innerclass.Outer;
import com.xhk.demo.interfaces.Content;

/**
 * @author xhk
 * @time 2018-11-27 10:19
 */
public class TestInnerClass {

	public static void main(String[] args) {
		Outer outer = new Outer("Outer");
		Content inner = outer.getInner();
		System.out.println(inner.value());
	}
}
