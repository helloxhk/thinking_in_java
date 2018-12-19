package com.xhk.demo.container;

import java.util.*;

/**
 * @author xhk
 * @time 2018-12-13 9:42
 */
public class Countries {

	public static final String[][] DATA = {
			{"ALGERIA", "Algiers"},
			{"ANGOLA", "Luanda"},
			{"BENIN", "Porto-Novo"},
			{"BOTSWANA", "Garberone"},
			{"BURKINA FASO", "Ouagadougou"},
			{"BURUNDI", "Bujumbura"},
	};

	private static class FlyweightMap extends AbstractMap<String, String> {

		private static class Entry implements Map.Entry<String, String> {

			private int index;

			public Entry(int index) {
				this.index = index;
			}

			@Override
			public String getKey() {
				return DATA[index][0];
			}

			@Override
			public String getValue() {
				return DATA[index][1];
			}

			@Override
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
		}

		private static class EntrySet extends AbstractSet<Map.Entry<String, String>> {

			private int size;

			public EntrySet(int size) {
				if (size < 0)
					this.size = 0;
				else if (size > DATA.length)
					this.size = DATA.length;
				else
					this.size = size;
			}

			private static class Iter implements Iterator<Map.Entry<String, String>> {

				private Entry entry = new Entry(-1);

				@Override
				public boolean hasNext() {
					return entry.index < DATA.length - 1;
				}

				@Override
				public Map.Entry<String, String> next() {
					entry.index++;
					return entry;
				}
			}

			@Override
			public Iterator<Map.Entry<String, String>> iterator() {
				return new Iter();
			}

			@Override
			public int size() {
				return this.size;
			}
		}

		private static Set<Map.Entry<String, String>> entrys = new EntrySet(DATA.length);

		@Override
		public Set<Map.Entry<String, String>> entrySet() {
			return entrys;
		}
	}

	public static Map<String, String> select(final int size) {
		return new FlyweightMap() {
			@Override
			public Set<Map.Entry<String, String>> entrySet() {
				return new FlyweightMap.EntrySet(size);
			}
		};
	}

	private static Map<String, String> map = new FlyweightMap();

	public static Map<String, String> capitals() {
		return map;
	}

	private static Map<String, String> capitals(int size) {
		return select(size);
	}

	private static List<String> names = new ArrayList<>(map.keySet());

	public static List<String> names() {
		return names;
	}

	public static List<String> names(int size) {
		return new ArrayList<>(select(size).keySet());
	}

	public static void main(String[] args) {
		System.out.println(capitals(6));
		System.out.println(names(6));
		System.out.println(new HashMap<>(capitals(3)));
		System.out.println(new LinkedHashMap<>(capitals(3)));
		System.out.println(new TreeMap<>(capitals(3)));
		System.out.println(new Hashtable<>(capitals(3)));
		System.out.println(new HashSet<>(names(6)));
		System.out.println(new LinkedHashSet<>(names(6)));
		System.out.println(new TreeSet<>(names(6)));
		System.out.println(new ArrayList<>(names(6)));
		System.out.println(new LinkedList<>(names(6)));
		System.out.println(capitals().get("BOTSWANA"));
	}
}

class MyMap extends AbstractMap<String, String> {
	@Override
	public Set<Entry<String, String>> entrySet() {
		return null;
	}
}
