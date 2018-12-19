package com.xhk.demo.selector;

public interface Selector {
	boolean end();
	Object current();
	void next();
}
