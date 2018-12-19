package com.xhk.demo.factory;

/**
 * @author xhk
 * @time 2018-11-26 15:20
 */
public class ChessFactory implements GameFactory {
	@Override
	public Game getGame() {
		return new Chess();
	}
}
