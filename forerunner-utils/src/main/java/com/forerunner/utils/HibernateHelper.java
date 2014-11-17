package com.forerunner.utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public final class HibernateHelper {
	private HibernateHelper() {
	}

	/**
	 * Get the real class type.
	 * In case of Hibernate proxies, return the entity type rather than the proxy's
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClass(T entity) {
		return Hibernate.getClass(entity);
	}

	public static void initialize(Object entity) {
		Hibernate.initialize(entity);
	}

	public static boolean isInitialized(Object entity) {
		return Hibernate.isInitialized(entity);
	}

	public static Object unproxy(Object value) {
		if (value instanceof HibernateProxy) {
			value = ((HibernateProxy) value).getHibernateLazyInitializer().getImplementation();
		}
		return value;
	}
}