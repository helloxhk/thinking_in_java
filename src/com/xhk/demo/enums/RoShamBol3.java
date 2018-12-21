package com.xhk.demo.enums;

import java.util.EnumMap;

/**
 * @author xhk
 * @time 2018-12-21 10:01
 */
public enum RoShamBol3 implements Competitor<RoShamBol3> {

	PAPAER,ROCK, SCISSOR;

	private static EnumMap<RoShamBol3, EnumMap<RoShamBol3, OutPut>> map = new EnumMap<>(RoShamBol3.class);

	static {
		for (RoShamBol3 ro : RoShamBol3.values()) {
			map.put(ro, new EnumMap<>(RoShamBol3.class));
		}
		initOption(PAPAER, OutPut.DRAW, OutPut.WIN, OutPut.LOSE);
		initOption(ROCK, OutPut.LOSE, OutPut.DRAW, OutPut.WIN);
		initOption(SCISSOR, OutPut.WIN, OutPut.LOSE, OutPut.DRAW);
	}

	private static void initOption(RoShamBol3 self, OutPut vPaper, OutPut vRock, OutPut vScissors) {
		EnumMap<RoShamBol3, OutPut> row = RoShamBol3.map.get(self);
		row.put(PAPAER, vPaper);
		row.put(ROCK, vRock);
		row.put(SCISSOR, vScissors);
	}

	@Override
	public OutPut compete(RoShamBol3 it) {
		return map.get(this).get(it);
	}

	public static void main(String[] args) {
		Util.play(RoShamBol3.class, 20);
	}
}
