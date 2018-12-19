package com.xhk.demo.greenhouse;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件控制器
 * @author xhk
 * @time 2018-11-28 10:07
 */
public class Controller {

	/**
	 * 事件列表
	 */
	private List<Event> events = new ArrayList<>();

	/**
	 * 添加事件
	 * @param event
	 */
	public void addEvent(Event event) {
		events.add(event);
	}

	/**
	 * 启动时间列表执行
	 */
	public void run() {
		while (events.size() > 0) {
			for (Event event : new ArrayList<>(events)) {
				if (event.ready()) {
					// 打印
					System.out.println(event);
					// 执行事件
					event.action();
					// 移除事件
					events.remove(event);
				}
			}
		}
	}

	public void addAll(List<Event> list) {
		events.addAll(list);
	}

}
