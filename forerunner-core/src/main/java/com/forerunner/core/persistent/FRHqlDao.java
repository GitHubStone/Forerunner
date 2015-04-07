package com.forerunner.core.persistent;

import java.util.List;
import java.util.Map;

public interface FRHqlDao {

	/**
	 * 检索所有对象(排序)
	 */
	public <T> List<T> listHQLOrderBy(Class<T> clazz, String orderBy, Object... args);

	/**
	 * 检索对象(where)
	 */
	public <T> List<T> listHQLWhere(Class<T> clazz, String where, Object... args);

	/**
	 * 检索对象(排序和where)
	 */
	public <T> List<T> listSimpleHQL(Class<T> clazz, String where, String orderby, Object... args);

	/**
	 * 获取唯一对象(HQL)
	 */
	public <T> T uniqueResultHQL(Class<T> clazz, String hql, Object... args);

	/**
	 * 获取唯一对象(通过过滤条件)
	 */
	public <T> T uniqueResultHQL(Class<T> clazz, FRCriteria criteria);

	/**
	 * 检索所有对象
	 */
	public <T> List<T> listHQL(Class<T> clazz);

	/**
	 * 检索对象(HQL)
	 */
	public <T> List<T> listHQL(Class<T> clazz, String hql, Object... args);

	/**
	 * 检索对象(HQL)
	 */
	public <T> List<T> listHQLWithMap(Class<T> clazz, String hql, Map<Object, Object> args);

	/**
	 * 检索对象(通过过滤条件)
	 */
	public <T> List<T> listHQL(Class<T> clazz, FRCriteria criteria);

	/**
	 * 获取对象的数量(通过过滤条件)
	 */
	public <T> Long countHQL(Class<T> clazz, FRCriteria criteria);

	/**
	 * 删除对象(通过HQL和过滤条件)
	 * ps: 此方法无法级联删除
	 */
	public <T> void deleteHQL(Class<T> clazz, FRCriteria criteria);
}
