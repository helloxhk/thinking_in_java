package com.xhk.demo.strategy;

/**
 * 组合器接口
 * @param <T>
 */
public interface Combiner<T> {
	T combine(T x, T y);
}
