package com.xhk.demo.reflect.dynamicproxy;

import java.util.*;

/**
 * @author xhk
 * @time 2018-12-10 10:21
 */
public class MainClass {

	public static void consumer(Interface1 obj) {
		obj.doSomething();
		obj.somethingElse("abc def ghi");
	}

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//		RealObject realObject = new RealObject();
		/*Interface1 object = (Interface1) Proxy.newProxyInstance(realObject.getClass().getClassLoader(),
				new Class[]{Interface1.class},
				new DynamicProxy(realObject));
		consumer(object);*/
//		Interface1 o = (Interface1) Proxy.newProxyInstance(Interface1.class.getClassLoader(),
//				new Class[]{Interface1.class},
//				new InvocationHandler() {
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//						System.out.println("..................before proxy..................");
//						Object result = method.invoke(realObject, args);
//						System.out.println("..................after proxy..................");
//						return result;
//					}
//				});
//		consumer(o);
//		Collections.emptyList();

		/*FieldDemo fd = new FieldDemo();
		System.out.println(fd);
		Field s1 = fd.getClass().getDeclaredField("s1");
		s1.setAccessible(true);
		System.out.println(s1.get(fd));
		s1.setAccessible(true);
		s1.set(fd,"other");
		System.out.println(s1.get(fd));
		System.out.println("---------------------------------");
		Field f = fd.getClass().getDeclaredField("f");
		f.setAccessible(true);
		System.out.println(f.get(fd));
		f.set(fd,"xxxxxxxxxxxxxxxxxx");
		System.out.println(f.get(fd));*/
//		test(new HashMap());
//		test(list());
//		MainClass.<Integer>test(1);
//		Object[] objs = new Object[]{1,2,3,5};
//		Integer[] ints2 = new Integer[2];
//		System.out.println(ints2);
//		Long[] ll = new Long[2];
//		System.out.println(ll);
//		String[] ss = new String[2];
//		System.out.println(ss);
//		List<Integer> list = new ArrayList<>();
//		System.out.println(list.getClass().getSimpleName());

//		List<Number> list11 = new ArrayList<>();
//		add(list11, new Integer(1));
//		add(list11, new Long(1));
//		System.out.println(list11.get(0));
//		System.out.println(list11.get(1));
		List<Object> list = null;
		add(list,null);
//		List<? super Number> ll = new ArrayList<>();
//		Object object = ll.get(0);
		ArrayList<?> lll = null;
		eq(lll,Long.parseLong("1"));
//		add(lll,new Long(1));
//		get(lll);
//		get(lll, new Long(1));
		ArrayList<?> s = null;
		List<? extends Number> ss = (List<? extends Number>) s;
	}

	public static <T> void eq(List<? extends T> list, T t) {
//		list.add(t);
	}

	public static <T> void add(List<? super T> list,T t) {
		list.add(t);
	}

	public static <T> T get(List<T> list, T t) {
		return list.get(0);
	}

	public static <T> ArrayList<T> list() {
		return new ArrayList<T>();
	}

	/*void f(List<String> list) {

	}
	void f(List<Integer> list) {

	}*/


}

class FieldDemo {

	private String s1 = "zhangsan";
	private static final String f = "final string";

	@Override
	public String toString() {
		return "FieldDemo{" +
				"s1='" + s1 + '\'' +
				'}';
	}

}