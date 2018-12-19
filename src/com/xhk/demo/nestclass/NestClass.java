package com.xhk.demo.nestclass;

/**
 * @author xhk
 * @time 2018-11-27 15:55
 */
public class NestClass {


	static class Inner {
		static int i = 1;
		void print() {
			System.out.println("nest class print");
		}

		static class InnerInner {
			void print() {
				System.out.println("nest nest class print()");
			}
		}
	}

	public static void main(String[] args) {
		new NestClass.Inner().print();
		new NestClass.Inner.InnerInner().print();
	}
}
