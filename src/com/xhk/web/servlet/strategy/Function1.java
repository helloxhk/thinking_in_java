package com.xhk.web.servlet.strategy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xhk
 * @time 2018-12-12 10:34
 */
public class Function1 {

	/**
	 * 归纳，将一组T 归纳为一个T
	 * @param seq
	 * @param combiner
	 * @param <T>
	 * @return
	 */
	public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
		Iterator<T> iterator = seq.iterator();
		if (iterator.hasNext()) {
			T result = iterator.next();
			while (iterator.hasNext()) {
				result = combiner.combine(result, iterator.next());
			}
			return result;
		}
		return null;
	}

	/**
	 * 遍历处理
	 * @param seq
	 * @param func
	 * @param <T>
	 * @return
	 */
	public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
		for (T t : seq) {
			func.function(t);
		}
		return func;
	}

	/**
	 * 将T 序列，转换为R序列
	 * @param seq
	 * @param func
	 * @param <R>
	 * @param <T>
	 * @return
	 */
	public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
		List<R> result = new ArrayList<>();
		for (T t : seq)
			result.add(func.function(t));
		return result;
	}

	/**
	 * 过滤
	 * @param seq
	 * @param pre
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pre) {
		List<T> result = new ArrayList<>();
		for (T t : seq) {
			if (pre.test(t)) {
				result.add(t);
			}
		}
		return result;
	}

	static class IntegerAdder implements Combiner<Integer> {
		@Override
		public Integer combine(Integer x, Integer y) {
			return x + y;
		}
	}

	static class IntegerSubtracter implements Combiner<Integer> {
		@Override
		public Integer combine(Integer x, Integer y) {
			return x - y;
		}
	}

	static class BigDecimalAdder implements Combiner<BigDecimal> {
		@Override
		public BigDecimal combine(BigDecimal x, BigDecimal y) {
			return x.add(y);
		}
	}

	static class BigIntegerAdder implements Combiner<BigInteger> {
		@Override
		public BigInteger combine(BigInteger x, BigInteger y) {
			return x.add(y);
		}
	}

	static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {
		private T bound;

		public GreaterThan(T bound) {
			this.bound = bound;
		}

		@Override
		public boolean test(T x) {
			return x.compareTo(bound) > 0;
		}
	}
}
