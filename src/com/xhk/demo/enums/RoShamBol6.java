package com.xhk.demo.enums;

/**
 * @author xhk
 * @time 2018-12-21 10:26
 */
public enum  RoShamBol6 implements Competitor<RoShamBol6> {

	PAPAER,ROCK, SCISSOR;

	private static OutPut[][] tables = {
			{OutPut.DRAW, OutPut.WIN, OutPut.LOSE}, //PAPAER
			{OutPut.LOSE, OutPut.DRAW, OutPut.WIN}, // ROCK
			{OutPut.WIN, OutPut.LOSE, OutPut.DRAW}, // SCISSOR
	};

	@Override
	public OutPut compete(RoShamBol6 other) {
		return tables[this.ordinal()][other.ordinal()];
	}

	public static void main(String[] args) {
		Util.play(RoShamBol6.class, 20);
	}
}
