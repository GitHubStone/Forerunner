package com.forerunner.core.persistent;

import java.util.Map;

/**
 * 条件查询
 * 
 * @author GQ
 */
public interface FRCriteria {
	/** 是否允许缓存 */
	public boolean isCachable();

	/** 是否允许排序 */
	public boolean allowSort();

	/** 是否允许过滤 */
	public boolean allowFilter();

	/** 是否允许分页 */
	public boolean allowPaging();

	/** 当前页号 */
	public int firstResult();

	/** 单页大小 */
	public int maxResults();

	/** 过滤条件 */
	public String where();

	/** 排序规则 */
	public String orderby();

	/** 动态参数 */
	public Map<? extends Object, ? extends Object> args();

	/** 负责语句 */
	public String complexHQL();
}