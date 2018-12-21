package com.xhk.demo.enums;

import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-20 17:51
 */
public enum RoShamBol2 implements Competitor<RoShamBol2>{

	PAPER(OutPut.DRAW, OutPut.LOSE, OutPut.WIN),
	SCISSORS(OutPut.WIN, OutPut.DRAW, OutPut.LOSE),
	ROCK(OutPut.LOSE, OutPut.WIN, OutPut.DRAW);

	private OutPut vPaper, vScissors, vRock;

	RoShamBol2(OutPut vPaper, OutPut vScissors, OutPut vRock) {
		this.vPaper = vPaper;
		this.vScissors = vScissors;
		this.vRock = vRock;
	}

	public OutPut compete(RoShamBol2 ro) {
		switch (ro) {
			default:
			case ROCK:
				return vRock;
			case PAPER:
				return vPaper;
			case SCISSORS:
				return vScissors;
		}
	}

	public static void main(String[] args) {
		Util.play(RoShamBol2.class, 20);
	}

	static RoShamBol2 get() {
		return RoShamBol2.class.getEnumConstants()[new Random().nextInt(3)];
	}
}

interface Competitor<T extends Competitor<T>>{
	OutPut compete(T t);
}

class Util {

	public static <T extends Competitor<T>> void match(T t1, T t2) {
		System.out.println(t1 + " vs " + t2 + " : " + t1.compete(t2));
	}

	public static <T extends Enum<T> & Competitor<T>> void play(Class<T> clazz, int size) {
		for (int i = 0; i < size; i++) {
			match(Enums.random(clazz),Enums.random(clazz));
		}

	}
}