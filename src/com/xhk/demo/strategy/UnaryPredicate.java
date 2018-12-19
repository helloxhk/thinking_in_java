package com.xhk.demo.strategy;

/**
 * 一元谓词，谓词是一个接受一个参数的表达式
 * @param <T>
 */
public interface UnaryPredicate<T> {
	boolean test(T x);
}
