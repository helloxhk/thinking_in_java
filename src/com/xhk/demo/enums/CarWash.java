package com.xhk.demo.enums;

import java.util.EnumSet;

/**
 * @author xhk
 * @time 2018-12-20 13:37
 */
public class CarWash {

	public enum Cycle {
		OP1{
			@Override
			void action() {
				System.out.println("OP1 action");
			}
		},
		OP2{
			@Override
			void action() {
				System.out.println("OP2 action");
			}
		},
		OP3{
			@Override
			void action() {
				System.out.println("OP3 action");
			}
		},
		OP4{
			@Override
			void action() {
				System.out.println("OP4 action");
			}
		};

		abstract void action();
	}

	EnumSet<Cycle> cycles = EnumSet.of(Cycle.OP1,Cycle.OP2);

	public void add(Cycle cycle) {
		cycles.add(cycle);
	}

	public void wash() {
		for (Cycle cycle : cycles) {
			cycle.action();
		}
	}

	@Override
	public String toString() {
		return cycles.toString();
	}

	public static void main(String[] args) {
		CarWash cw = new CarWash();
		System.out.println(cw);
		cw.wash();


		cw.add(Cycle.OP1);
		cw.add(Cycle.OP3);
		cw.add(Cycle.OP3);
		System.out.println(cw);
		cw.wash();
	}
}
