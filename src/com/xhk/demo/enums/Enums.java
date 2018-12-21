package com.xhk.demo.enums;

import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-20 10:40
 */
public class Enums {

	private static Random rand = new Random();

	public static <T extends Enum<T>> T random(Class<T> clazz) {
		return random(clazz.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}

enum Acticity {
	SITTING,LYING,STANDING,HOPPING,
	RUNNING,DODGING,JUMPING,FLYING, FALLING
}

class Test {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.print(Enums.random(Acticity.class) + " ");
			if (i == 9)
				System.out.println();
		}
	}
}
