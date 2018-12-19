package com.xhk.demo.factory;

/**
 * @author xhk
 * @time 2018-11-26 15:15
 */
public class Checkers implements Game {

	private int moves = 1;

	private static final int MOVES = 3;

	@Override
	public boolean move() {
		System.out.println("cheker move " + moves);
		return moves++ != MOVES;
	}
}
