package com.xhk.demo.domainClass;

/**
 * @author xhk
 * @time 2018-11-27 13:17
 */
public class Test {
	public static void main(String[] args) {
		Destination p_destination = new Domain().getDestination("P Destination");
		p_destination.print();
	}
}
