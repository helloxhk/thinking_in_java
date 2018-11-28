package com.xhk.web.servlet.interfaces;

public interface E {
	interface G {
		void f();
	}

	public interface H {
		void f();
	}

	void g();

//	private interface I {
//
//	}

	class Demo {
		void print() {
			System.out.println("ssssssss");
		}
	}

	static void main(String[] args) {
		E e = new E() {
			@Override
			public void g() {

			}
		};

		E.Demo demo = new E.Demo();
		demo.print();
	}
}
