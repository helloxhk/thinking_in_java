package com.xhk.demo.selector;

/**
 * @author xhk
 * @time 2018-11-27 9:44
 */
public class Sequence {
	private int next = 0;
	private Object[] items;

	public Sequence(int size) {
		items = new Object[size];
	}

	public void add(Object object) {
		if(next < items.length)
			items[next++] = object;
	}

	private class SequenceSelector implements Selector {

		private int i = 0;

		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if (i < items.length)
				i++;
		}

		Sequence sequence() {
			return Sequence.this;
		}
	}

	Selector selector(){
		return new SequenceSelector();
	}

	public static void main(String[] args) {
		Sequence s = new Sequence(10);
		for (int i = 0; i < 10; i++) {
			s.add(new Demo("Strnig " + i));
		}
		Selector selector = s.selector();
		while (!selector.end()){
			System.out.println(selector.current());
			selector.next();
		}
	}

}

class Demo {

	String string;

	public Demo(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "Demo{" +
				"string='" + string + '\'' +
				'}';
	}
}