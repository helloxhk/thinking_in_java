package com.xhk.web.servlet.factory;

/**
 * @author xhk
 * @time 2018-11-26 15:17
 */
public class Chess implements Game {

	private int moves = 1;

	private static final int MOVES = 4;

	@Override
	public boolean move() {
		System.out.println("chess move " + moves);
		return moves++ != MOVES;
	}
}
