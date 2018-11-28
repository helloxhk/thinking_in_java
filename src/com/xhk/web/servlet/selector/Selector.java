package com.xhk.web.servlet.selector;

public interface Selector {
	boolean end();
	Object current();
	void next();
}
