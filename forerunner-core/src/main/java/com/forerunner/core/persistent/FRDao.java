package com.forerunner.core.persistent;

import java.io.Serializable;
import java.util.Map;

public interface FRDao {
	public <T> T get(Class<T> clazz, Serializable id);

	/**
	 * 获取对象(代理对象延时加载)
	 */
	public <T> T load(Class<T> clazz, Serializable id);

	/**
	 * 保存对象
	 */
	public <T> Long save(T entity);

	/**
	 * 保存或更新对象
	 */
	public <T> void saveOrUpdate(T entity);

	/**
	 * 删除对象(先通过ID将对象加载到session中)
	 */
	public <T> void delete(Class<T> clazz, Long id);

	/**
	 * 删除对象
	 */
	public <T> void delete(T entity);

	/**
	 * 删除对象(通过HQL和过滤条件)
	 */
	public <T> void delete(String hql, FRCriteria criteria);

	public int bulkUpdate(String hql, Map<? extends Object, ? extends Object> args);
}
