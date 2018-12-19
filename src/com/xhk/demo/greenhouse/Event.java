package com.xhk.demo.greenhouse;

/**
 * 事件抽象类
 * @author xhk
 * @time 2018-11-28 10:02
 */
public abstract class Event {

	/**
	 * 事件触发事件
	 */
	private long eventTime;

	/**
	 * 推迟时间
	 */
	protected long delayTime;

	public Event(long delayTime) {
		this.delayTime = delayTime;
	}

	/**
	 * 是否准备好执行
	 * @return
	 */
	public boolean ready() {
		return System.nanoTime() >= eventTime;
	}

	/**
	 * 设置/重置启动时间
	 */
	public void start() {
		this.eventTime = System.nanoTime() + delayTime;
	}

	/**
	 * 执行动作
	 */
	public abstract void action();
}