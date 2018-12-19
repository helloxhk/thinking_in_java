package com.xhk.demo.container;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-13 15:44
 */
public class QueueTest {

	public static void main(String[] args) {
		PriorityQueue<IntegerItem> queue = new PriorityQueue<>();
		queue.add(new IntegerItem(2));
		queue.add(new IntegerItem(4));
		queue.add(new IntegerItem(7));
		queue.add(new IntegerItem(1));
		queue.add(new IntegerItem(2));
		System.out.println(queue);

		PriorityQueue<Integer> sss = new PriorityQueue<>();
		sss.add(2);
		sss.add(4);
		sss.add(7);
		sss.add(1);
		sss.add(2);
		System.out.println(sss);
	}
}

class IntegerItem implements Comparable<IntegerItem> {

	private static Random random = new Random();

	private Integer i;

	public IntegerItem(int i) {
//		this.i = random.nextInt(100);
		this.i = i;
	}

	@Override
	public int compareTo(IntegerItem o) {
		return i - o.i < 0 ? -1 : i - o.i == 0 ? 0 : 1;
	}

	@Override
	public String toString() {
		return "IntegerItem{" +
				"i=" + i +
				'}';
	}
}
