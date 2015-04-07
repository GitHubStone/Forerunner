package com.forerunner.old;

import com.forerunner.core.persistent.Trace;

/**
 * 计划目标
 * @author GQ
 */
public class Target extends Trace {
	private static final long serialVersionUID = -7927371012699526368L;

	private Long id;

	private Long importantLevel;//重要程度
	private Long difficultyLevel;//完成难度
	private Long progress;//进度 percentage

	private Schedule review;//review time

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}