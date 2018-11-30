package com.xhk.web.servlet.inheritinner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xhk
 * @time 2018-11-28 16:06
 */
public class InheritInner extends WithInner.Inner {

	public InheritInner(WithInner wi) {
		wi.super();
	}

	public static void main(String[] args) {
		/*WithInner wi = new WithInner();
		wi.setString("xxxxxxxxxxxxxx");
		InheritInner ii = new InheritInner(wi);
		ii.print();*/

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//		list.add(1);
//		Collections.addAll(list, 1,2,3);
	}
}
