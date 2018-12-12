package com.xhk.web.servlet.strategy;

/**
 * 收集器接口
 *
 * @param <T>
 */
public interface Collector<T> extends UnaryFunction<T, T> {
	T result();
}
