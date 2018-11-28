package com.xhk.web.servlet;

import com.xhk.web.servlet.innerclass.Outer;
import com.xhk.web.servlet.interfaces.Content;

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
