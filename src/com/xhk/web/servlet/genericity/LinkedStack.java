package com.xhk.web.servlet.genericity;

/**
 * @author xhk
 * @time 2018-12-10 14:08
 */
public class LinkedStack<T> {

	private int size = 0;

	private Node top = new Node();

	public void push(T item) {
		top = new Node(item, top);
		size++;
	}

	public T pop() {
		T result = top.item;
		if (!top.end()) {
			size--;
			top = top.next;
		}
		return result;
	}

	public int size() {
		return size;
	}

	private class Node {
		T item;
		Node next;

		public Node() {
		}

		public Node(T item, Node next) {
			this.item = item;
			this.next = next;
		}

		public boolean end() {
			return item == null && next == null;
		}
	}

	public static void main(String[] args) {
		LinkedStack<Integer> integers = new LinkedStack<>();
		integers.push(1);
		integers.push(2);
		integers.push(3);
		integers.push(4);
		integers.push(5);
		Integer i;
		System.out.println("size : " + integers.size());
		while ((i = integers.pop()) != null) {
			System.out.println(i);
		}
	}
}
