package com.xhk.demo.enums;

/**
 * @author xhk
 * @time 2018-12-20 9:53
 */
public class TrafficLight {

	private Signal color = Signal.RED;

	public void change() {
		switch (color) {
			case RED:
				color = Signal.GREEN;
				break;
			case GREEN:
				color = Signal.YELLOW;
				break;
			case YELLOW:
				color = Signal.RED;
				break;
		}
	}

	@Override
	public String toString() {
		return "TrafficLight color is " + color;
	}

	public static void main(String[] args) {

		TrafficLight tl = new TrafficLight();

		for (int i = 0; i < 7; i++) {
			System.out.println(tl);
			tl.change();
		}

		for (Object obj : XX.class.getEnumConstants()) {
			System.out.println(obj);
		}
	}

	enum XX {
		A,B, C,
	}
}

enum Signal {
	RED,
	GREEN,
	YELLOW
}
