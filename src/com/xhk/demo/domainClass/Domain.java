package com.xhk.demo.domainClass;

/**
 * @author xhk
 * @time 2018-11-27 11:20
 */
public class Domain {

	Destination destination(String string) {
		class DDestination implements Destination {

			private String string;

			public DDestination(String string) {
				this.string = string;
			}

			@Override
			public void print() {
				System.out.println(string);
			}
		}
		return new DDestination(string);
	}

	private class PDestination implements Destination {

		private String string;

		public PDestination(String string) {
			this.string = string;
		}

		@Override
		public void print() {
			System.out.println(string);
		}
	}

	Destination getDestination(String string) {
		return new PDestination(string);
	}

	Destination getDestination2(Object sss) {

//		sss = new Object();
		return new Destination() {
			{
				System.out.println("init");
			}
			{
				System.out.println("aaaaaaaaa");
			}
			private String str = sss.toString();
			@Override
			public void print() {
				System.out.println(str);
			}
		};
	}


	public static void main(String[] args) {
		Domain domain = new Domain();
		Destination p_destination = domain.getDestination("P Destination");
		PDestination pDestination = (PDestination) p_destination;
		pDestination.print();
		domain.getDestination2("ssssssss").print();
	}

}

interface Destination {
	void print();
}