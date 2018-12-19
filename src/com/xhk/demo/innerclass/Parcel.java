package com.xhk.demo.innerclass;

/**
 * @author xhk
 * @time 2018-11-26 16:01
 */
public class Parcel {

	class Contents {
		private int i = 11;
		public int value() {
			return i;
		}
	}

	class Destination {
		private String label;

		public Destination(String whereTo) {
			this.label = whereTo;
		}

		String readLabel() {
			return label;
		}
	}

	public Destination to(String s) {
		return new Destination(s);
	}

	public Contents contents() {
		return new Contents();
	}

	public void ship(String dest) {
		Contents c = new Contents();
		Destination des = new Destination(dest);
		System.out.println(des.readLabel());
	}

	public static void main(String[] args) {
		Parcel parcel = new Parcel();
		Parcel.Contents contents = new Parcel().new Contents();

	}
}
