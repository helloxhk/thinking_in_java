package com.xhk.demo.enums;

import java.util.Random;

/**
 * @author xhk
 * @time 2018-12-20 16:55
 */
public class RoShamBol {

	static Random random = new Random();

	static Item newItem() {
		switch (random.nextInt(3)) {
			default:
			case 0:
				return new Item.Rock();
			case 1:
				return new Item.Scissors();
			case 2:
				return new Item.Paper();
		}
	}

	static void match(Item a, Item b) {
		System.out.println(a + " vs " + b + " : " + a.compete(b));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			match(newItem(), newItem());
		}
	}

}

interface Item {

	OutPut compete(Item item);
	OutPut eval(Paper paper);
	OutPut eval(Scissors scissors);
	OutPut eval(Rock rock);

	class Paper implements Item {
		@Override
		public OutPut compete(Item item) {
			return item.eval(this);
		}

		@Override
		public OutPut eval(Paper paper) {
			return OutPut.DRAW;
		}

		@Override
		public OutPut eval(Scissors scissors) {
			return OutPut.WIN;
		}

		@Override
		public OutPut eval(Rock rock) {
			return OutPut.LOSE;
		}

		@Override
		public String toString() {
			return "Paper";
		}
	}

	class Scissors implements Item {

		@Override
		public OutPut compete(Item item) {
			return item.eval(this);
		}

		@Override
		public OutPut eval(Paper paper) {
			return OutPut.LOSE;
		}

		@Override
		public OutPut eval(Scissors scissors) {
			return OutPut.DRAW;
		}

		@Override
		public OutPut eval(Rock rock) {
			return OutPut.WIN;
		}

		@Override
		public String toString() {
			return "Scissors";
		}
	}

	class Rock implements Item {

		@Override
		public OutPut compete(Item item) {
			return item.eval(this);
		}

		@Override
		public OutPut eval(Paper paper) {
			return OutPut.WIN;
		}

		@Override
		public OutPut eval(Scissors scissors) {
			return OutPut.LOSE;
		}

		@Override
		public OutPut eval(Rock rock) {
			return OutPut.DRAW;
		}

		@Override
		public String toString() {
			return "Rock";
		}
	}

}

enum OutPut {
	WIN, LOSE, DRAW
}
