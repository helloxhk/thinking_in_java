package com.xhk.demo.strategy;

/**
 * 一元函数
 *
 * @author xhk
 * @time 2018-12-12 10:29
 */
public interface UnaryFunction<R, T> {
	R function(T t);
}
