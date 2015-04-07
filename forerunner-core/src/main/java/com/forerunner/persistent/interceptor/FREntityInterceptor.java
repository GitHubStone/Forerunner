package com.forerunner.persistent.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.forerunner.persistent.PersistentConstants;
import com.forerunner.persistent.entity.Trace;
import com.forerunner.um.UMContext;
import com.forerunner.utils.DateUtils;
import com.google.common.collect.Lists;

/**
 * entity intercept
 * @author GQ
 */
public class FREntityInterceptor extends EmptyInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("frEntityEventPublisher")
	private FREntityEventPublisher eventPublisher;//事件发布者

	private ThreadLocal<Boolean> notice = new ThreadLocal<Boolean>();//是否需要通知

	private ThreadLocal<List<FREntityEvent>> entityEvents = new ThreadLocal<List<FREntityEvent>>();//事务中是发生的所有事件

	/**
	 * 更新
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		setNotice(true);

		updateBaseEntity(entity, currentState, propertyNames);

		addEvent(entity, id, currentState, propertyNames, PersistentConstants.EVENT_TYPE_UPDATE);
		return true;
	}

	/**
	 * 保存
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		setNotice(true);

		updateBaseEntity(entity, state, propertyNames);

		addEvent(entity, id, state, propertyNames, PersistentConstants.EVENT_TYPE_CREATE);
		return true;
	}

	/**
	 * 删除
	 */
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		setNotice(true);

		addEvent(entity, id, state, propertyNames, PersistentConstants.EVENT_TYPE_DELETE);
	}

	/**
	 * 开始事务后首个处理
	 */
	@Override
	public void afterTransactionBegin(Transaction tx) {
		clearInterceptorCache();
	}

	/**
	 * 完成事务后首个处理
	 */
	@Override
	public void afterTransactionCompletion(Transaction tx) {
		if (!isNotice()) {
			return;
		}
		if (tx.wasRolledBack()) {
			if (log.isDebugEnabled()) {
				System.err.println("Transaction was rolled back.");
			}
			clearInterceptorCache();
			return;
		}

		List<FREntityEvent> events = Lists.newArrayList();//prevent ConcurrentModificationException
		for (FREntityEvent entityEvent : getEntityEvents()) {
			events.add(entityEvent);
		}

		if (log.isDebugEnabled()) {
			System.err.println(Thread.currentThread().getName() + " : " + events);
		}

		for (FREntityEvent event : events) {
			eventPublisher.publishEvent(event);
		}

		clearInterceptorCache();
	}

	/**
	 * 记录事件(事务级别)
	 */
	private void addEvent(Object entity, Serializable id, Object[] state, String[] propertyNames, String eventType) {
		FREntityEvent entityEvent = new FREntityEvent();

		entityEvent.setType(eventType);
		entityEvent.setId(id);
		entityEvent.setClazz(entity.getClass());
		for (String propertyName : propertyNames) {
			entityEvent.putProperty(propertyName, getValue(state, propertyNames, propertyName));
		}
		entityEvent.putProperty("id", id);

		getEntityEvents().add(entityEvent);
	}

	/**
	 * 更新所有基于BaseEntity的实体
	 */
	private void updateBaseEntity(Object entity, Object[] currentState, String[] propertyNames) {
		if (entity instanceof Trace) {
			setValue(currentState, propertyNames, "updateBy", UMContext.getCurrentContext().getCurrentUser());
			setValue(currentState, propertyNames, "updateOn", DateUtils.today(Date.class));
			if (getValue(currentState, propertyNames, "createBy") == null) {
				setValue(currentState, propertyNames, "createBy", UMContext.getCurrentContext().getCurrentUser());
			}
			if (getValue(currentState, propertyNames, "createOn") == null) {
				setValue(currentState, propertyNames, "createOn", DateUtils.today(Date.class));
			}
		}
	}

	private void setValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object value) {
		int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		if (index >= 0) {
			currentState[index] = value;
		}
	}

	private Object getValue(Object[] currentState, String[] propertyNames, String propertyToGet) {
		int index = Arrays.asList(propertyNames).indexOf(propertyToGet);
		return currentState[index];
	}

	private void clearInterceptorCache() {
		this.notice.remove();
		this.entityEvents.remove();
	}

	private boolean isNotice() {
		Boolean notice = this.notice.get();
		if (notice == null) {
			notice = false;
			this.notice.set(notice);
		}
		return notice;
	}

	private void setNotice(boolean notice) {
		this.notice.set(notice);
	}

	private List<FREntityEvent> getEntityEvents() {
		List<FREntityEvent> events = this.entityEvents.get();
		if (events == null) {
			events = Lists.newArrayList();
			this.entityEvents.set(events);
		}
		return events;
	}
}