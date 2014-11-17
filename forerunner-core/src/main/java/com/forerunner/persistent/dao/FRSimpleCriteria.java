package com.forerunner.persistent.dao;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 过滤条件 简单适配器
 * 
 * @author GQ
 * @see FRCriteria
 */
public class FRSimpleCriteria implements FRCriteria {
	private Map<Object, Object> args = Maps.newHashMap();
	private String where = null;

	public FRSimpleCriteria() {
		super();
	}

	public FRSimpleCriteria(Object[] args) {
		super();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				this.args.put(i, args[i]);
			}
		}
	}

	public FRSimpleCriteria(Map<Object, Object> args) {
		super();
		this.args = args;
	}

	public FRSimpleCriteria(String where, Map<Object, Object> args) {
		super();
		this.where = where;
		this.args = args;
	}

	@Override
	public Map<? extends Object, ? extends Object> args() {
		return args;
	}

	@Override
	public boolean isCachable() {
		return false;
	}

	@Override
	public boolean allowFilter() {
		return true;
	}

	@Override
	public boolean allowSort() {
		return true;
	}

	@Override
	public boolean allowPaging() {
		return false;
	}

	@Override
	public String where() {
		return where;
	}

	@Override
	public String orderby() {
		return null;
	}

	@Override
	public int firstResult() {
		return 0;
	}

	@Override
	public int maxResults() {
		return 0;
	}

	@Override
	public String complexHQL() {
		return null;
	}
}