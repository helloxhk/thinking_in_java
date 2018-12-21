package com.xhk.demo.enums;

/**
 * @author xhk
 * @time 2018-12-20 9:44
 */
public enum  OzWitch {
	NORTH("north description"),
	SOUTH("south description"),
	EAST("east description"),
	WEST("west description");

	private String description;

	OzWitch(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		String id = name();
		String lower = id.substring(1).toLowerCase();
		return id.charAt(0) + lower;
	}

	public static void main(String[] args) {
		for (OzWitch ozWitch : OzWitch.values()) {
			System.out.println(ozWitch.getDescription() + " " + ozWitch);
		}
	}
}
