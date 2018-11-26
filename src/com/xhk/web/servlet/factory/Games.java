package com.xhk.web.servlet.factory;

/**
 * @author xhk
 * @time 2018-11-26 15:18
 */
public class Games {

	public static void playGames(GameFactory g) {
		Game game = g.getGame();
		while (game.move())
			;
	}

	public static void main(String[] args) {
		playGames(new CheckersFactory());
		playGames(new ChessFactory());
	}
}
