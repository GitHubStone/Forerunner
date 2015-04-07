package com.forerunner.core.persistent;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class FRHqlDaoImpl extends FRDaoImpl implements FRHqlDao {

	/**
	 * 检索所有对象(排序)
	 */
	public <T> List<T> listHQLOrderBy(Class<T> clazz, String orderBy, Object... args) {
		String hql = buildSimpleHQL(clazz.getName(), null, orderBy);
		return this.list(hql, clazz, new FRSimpleCriteria(args));
	}

	/**
	 * 检索对象(where)
	 */
	public <T> List<T> listHQLWhere(Class<T> clazz, String where, Object... args) {
		String hql = buildSimpleHQL(clazz.getName(), where, null);
		return this.list(hql, clazz, new FRSimpleCriteria(args));
	}

	/**
	 * 检索对象(排序和where)
	 */
	public <T> List<T> listSimpleHQL(Class<T> clazz, String where, String orderby, Object... args) {
		String hql = buildSimpleHQL(clazz.getName(), where, orderby);
		return this.list(hql, clazz, new FRSimpleCriteria(args));
	}

	/**
	 * 获取唯一对象(HQL)
	 */
	public <T> T uniqueResultHQL(Class<T> clazz, String hql, Object... args) {
		return (T) this.uniqueResult(hql, clazz, new FRSimpleCriteria(args));
	}

	/**
	 * 获取唯一对象(通过过滤条件)
	 */
	public <T> T uniqueResultHQL(Class<T> clazz, FRCriteria criteria) {
		String hql = getHQLByCriteria(clazz, criteria);
		return (T) this.uniqueResult(hql, clazz, criteria);
	}

	/**
	 * 检索所有对象
	 */
	public <T> List<T> listHQL(Class<T> clazz) {
		String hql = buildSimpleHQL(clazz.getName(), null, null);
		return this.list(hql, clazz, new FRSimpleCriteria(Maps.newHashMap()));
	}

	/**
	 * 检索对象(HQL)
	 */
	public <T> List<T> listHQL(Class<T> clazz, String hql, Object... args) {
		return this.list(hql, clazz, new FRSimpleCriteria(args));
	}

	/**
	 * 检索对象(HQL)
	 */
	public <T> List<T> listHQLWithMap(Class<T> clazz, String hql, Map<Object, Object> args) {
		return this.list(hql, clazz, new FRSimpleCriteria(args));
	}

	/**
	 * 检索对象(通过过滤条件)
	 */
	public <T> List<T> listHQL(Class<T> clazz, FRCriteria criteria) {
		String hql = getHQLByCriteria(clazz, criteria);
		return this.list(hql, clazz, criteria);
	}

	/**
	 * 获取对象的数量(通过过滤条件)
	 */
	public <T> Long countHQL(Class<T> clazz, FRCriteria criteria) {
		String hql = "select count(*) " + getHQLByCriteria(clazz, criteria);
		return this.uniqueResult(hql, Long.class, criteria);
	}

	/**
	 * 构建简单的HQL
	 * 
	 * e.g : "form...where...order by..."
	 */
	private String buildSimpleHQL(String from, String where, String orderby) {
		String hql = "";
		if (StringUtils.isNotBlank(from))
			hql += "from " + from;
		if (StringUtils.isNotBlank(where))
			hql += " where " + where;
		if (StringUtils.isNotBlank(orderby))
			hql += " order by " + orderby;
		return hql;
	}

	/**
	 * 获取HQL 通过过滤条件 拼装HQL 包括 filter、 sort 和 complex HQL
	 */
	private <T> String getHQLByCriteria(Class<T> clazz, FRCriteria criteria) {
		String where = null;
		String orderby = null;
		if (criteria != null) {
			if (criteria.complexHQL() != null)
				return criteria.complexHQL();

			//filter
			if (criteria.allowFilter())
				where = criteria.where();
			//sort
			if (criteria.allowSort())
				orderby = criteria.orderby();
		}
		//hql
		String hql = buildSimpleHQL(clazz.getName(), where, orderby);
		return hql;
	}

	/**
	 * 删除对象(通过HQL和过滤条件)
	 * ps: 此方法无法级联删除
	 */
	public <T> void deleteHQL(Class<T> clazz, FRCriteria criteria) {
		String hql = "delete " + clazz.getName() + " where " + criteria.where();
		this.delete(hql, criteria);
	}
}