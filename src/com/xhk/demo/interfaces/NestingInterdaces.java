package com.xhk.demo.interfaces;

/**
 * @author xhk
 * @time 2018-11-26 14:30
 */
public class NestingInterdaces {
	public class BImpl implements A.B {
		@Override
		public void f() {

		}
	}

	public class CImpl implements A.C {
		@Override
		public void f() {

		}
	}

//	class DImpl implements A.D {
//		@Override
//		public void f() {
//
//		}
//	}

	class EImpl implements E {
		@Override
		public void g() {

		}
	}

	class EGImpl implements E.G {
		@Override
		public void f() {

		}
	}

	class EGImpl2 implements E {
		@Override
		public void g() {

		}

		class EG implements E.G {
			@Override
			public void f() {

			}
		}
	}

	public static void main(String[] args) {
		A a = new A();
//		A.D d = a.getD();
		A.DImpl2 d = (A.DImpl2) a.getD();
		d.f();
//		a.getD().f();
		A a2 = new A();
		a2.receiveD(a.getD());
	}

	class AImpl implements A.C {
		@Override
		public void f() {

		}

		class DImpl implements A.C {
			@Override
			public void f() {

			}
		}
	}

}
