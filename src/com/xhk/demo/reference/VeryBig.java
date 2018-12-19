package com.xhk.demo.reference;

/**
 * @author xhk
 * @time 2018-12-17 10:31
 */
public class VeryBig {

	private static final int SIZE = 10000;

	private long[] ls = new long[SIZE];

	private String ident;

	public VeryBig(String ident) {
		this.ident = ident;
	}

	public String getIdent() {
		return ident;
	}

	@Override
	public String toString() {
		return ident;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing " + ident);
	}
}
