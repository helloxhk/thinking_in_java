package com.xhk.web.servlet.reference;

import java.util.Objects;
import java.util.WeakHashMap;

/**
 * @author xhk
 * @time 2018-12-17 10:57
 */
public class WeakHashMapTest {

	public static void main(String[] args) {
		int size = 1000;
		Key[] keys = new Key[size];

		WeakHashMap<Key, Value> whm = new WeakHashMap<>();
		for (int i = 0; i < size; i++) {
			Key key = new Key(String.valueOf(i));
			Value value = new Value(String.valueOf(i));
			if (i % 3 == 0) {
				keys[i] = key;
			}
			whm.put(key, value);
		}
		System.gc();
	}

}

class Element {

	private String ident;

	public Element(String ident) {
		this.ident = ident;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		Element element = (Element) object;
		return Objects.equals(ident, element.ident);
	}

	@Override
	public int hashCode() {

		return Objects.hash(ident);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
	}
}

class Key extends Element {
	public Key(String ident) {
		super(ident);
	}
}

class Value extends Element {
	public Value(String ident) {
		super(ident);
	}
}
