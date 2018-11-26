package com.xhk.web.servlet;

/**
 * @author xhk
 * @time 2018-11-08 10:04
 */
public abstract class Test {

	private final int i = 1;

	{
		System.out.println("111");
	}

	public abstract void sss();

	public static void printArray(Object... args) {
		for (Object arg : args)
			System.out.print(arg + " ");
		System.out.println(args.getClass());
	}

	public static void main(String[] args) {

		/*System.out.println(InterfaceTest.i);*/
		/*Book subBook = new SubBook();
		System.out.println(subBook.i);*/
		/*String result = MessageFormat.format("你好，{0}先生", 999L);
		System.out.println(result);*/

		/*printArray(new Integer[]{1,2,3,4,5,6});
		printArray(1,2,3,4,5,6);
		printArray(new String[]{"a", "b", "c", "c", "e"});
		printArray("a", "b", "c", "c", "e");*/

		/*printArray();
		printArray();
		System.out.println(new int[0].getClass());
		System.out.println(new Integer[0].getClass());*/

		/*System.out.println(new ArrayList<Integer>(){{
			add(1);
			add(2);
		}});*/
		/*System.out.println(new Book(false));
		System.out.println(new Book(false));*/

		/*Long l1 = new Long(1);
		Long l2 = new Long(1);
		System.out.println(l1.equals(l2));*/
		/*FinalTest test1 = new FinalTest();
		FinalTest test2 = new FinalTest();
		System.out.println(test1.i1);
		System.out.println(test1.i2);
		System.out.println(test2.i1);
		System.out.println(test2.i2);*/
//		final int[] fInt = new int[2];

		}
		}
class Book {

	int i = 10;

	static String str = generateString();

	private String str1 = option("Book field init");

	public Book() {
		System.out.println("Book constructor");
	}

	public static String option(String string) {
		System.out.println(string);
		return "init";
	}

	public static String generateString(){
		System.out.println("super	generateString");
		return "super String";
	}

	void test(){

	}
}

class SubBook extends Book {

	int i = 20;

	static String str = generateString();

	private String str2 = option("SubBook field init");

	public SubBook() {
		System.out.println("SubBook constructor");
	}

	public static String generateString(){
		System.out.println("sub	generateString");
		return "Sub String";
	}

	@Override
	final void test(){

	}
}

class SubTest extends Test {

	@Override
	public void sss() {

	}
}