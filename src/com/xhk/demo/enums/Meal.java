package com.xhk.demo.enums;

/**
 * @author xhk
 * @time 2018-12-20 10:59
 */
public enum Meal {

	MAINCOURSE(Food.MainCourse.class),
	COFFEE(Food.Coffee.class);

	private Food[] values;

	Meal(Class<? extends Food> clazz) {
		values = clazz.getEnumConstants();
	}

	interface Food {
		enum MainCourse implements Food {
			LASAGNE,BURRITO,PAD_THAT,
			LENTILS,HUMMOUS,VINDALOO
		}
		enum Coffee implements Food {
			BLACK_COFFEE,DECAF_COFFEE,ESPRESSO,
			LATTE,TEA
		}
	}

	public Food randomSelection() {
		return Enums.random(values);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			for (Meal meal : Meal.values()) {
				Food food = meal.randomSelection();
				System.out.println(food);
			}
		}
	}
}
