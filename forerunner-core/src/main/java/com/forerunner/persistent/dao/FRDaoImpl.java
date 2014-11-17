package com.forerunner.persistent.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 最基础的数据访问对象，直接操作 Hibernate session。
 * @author GQ
 */
public class FRDaoImpl {
	Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	//获取线程级别session
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 获取对象
	 */
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	/**
	 * 获取对象
	 */
	public <T> T get(Class<T> clazz, Serializable id, LockOptions lockOptions) {
		return (T) getSession().get(clazz, id, lockOptions);
	}

	/**
	 * 获取对象(代理对象延时加载)
	 */
	public <T> T load(Class<T> clazz, Serializable id) {
		return (T) this.getSession().load(clazz, id);
	}

	/**
	 * 保存对象
	 */
	public <T> Long save(T object) {
		return (Long) this.getSession().save(object);
	}

	/**
	 * 保存或更新对象
	 */
	public <T> void saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
	}

	/**
	 * 删除对象(先通过ID将对象加载到session中)
	 */
	public <T> void delete(Class<T> clazz, long id) {
		getSession().delete(get(clazz, id));
	}

	/**
	 * 删除对象
	 */
	public <T> void delete(T object) {
		this.getSession().delete(object);
	}

	/**
	 * 删除对象(通过HQL和过滤条件)
	 */
	public <T> void delete(String hql, FRCriteria criteria) {
		Query query = createQueryAndSetArgs(hql, criteria);
		query.executeUpdate();
	}

	public int bulkUpdate(String hql, Map<? extends Object, ? extends Object> args) {
		if (log.isDebugEnabled()) {
			log.debug("Create Query - " + hql);
		}
		Query query = getSession().createQuery(hql);
		setArguments(query, args);
		return query.executeUpdate();
	}

	/**
	 * 获取唯一对象(通过HQL和过滤条件)
	 */
	protected <T> T uniqueResult(String hql, Class<T> c, FRCriteria criteria) {
		Query query = createQueryAndSetArgs(hql, criteria);
		return (T) query.uniqueResult();
	}

	/**
	 * 检索对象(通过HQL和过滤条件)
	 */
	protected <T> List<T> list(String hql, Class<T> c, FRCriteria criteria) {
		Query query = createQueryAndSetArgs(hql, criteria);
		return query.list();
	}

	/**
	 * 创建Query 拼装条件 中的分页和参数
	 */
	private Query createQueryAndSetArgs(String hql, FRCriteria criteria) {
		if (log.isDebugEnabled()) {
			log.debug("Create Query - " + hql);
		}
		Query query = getSession().createQuery(hql);

		if (criteria == null)
			return query;

		if (criteria.isCachable())
			query.setCacheable(true);

		//paging
		if (criteria.allowPaging()) {
			query.setFirstResult(criteria.firstResult());
			query.setMaxResults(criteria.maxResults());
		}

		//arguments
		setArguments(query, criteria.args());
		return query;
	}

	private void setArguments(Query query, Map<? extends Object, ? extends Object> args) {
		if (MapUtils.isEmpty(args))
			return;

		for (Map.Entry<? extends Object, ? extends Object> entry : args.entrySet()) {
			if (entry.getValue() instanceof Collection) {
				query.setParameterList((String) entry.getKey(), (Collection<?>) entry.getValue());
				continue;
			}
			if (entry.getKey() instanceof Integer) {
				query.setParameter((Integer) entry.getKey(), entry.getValue());
			}
			if (entry.getKey() instanceof String) {
				query.setParameter((String) entry.getKey(), entry.getValue());
			}
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}