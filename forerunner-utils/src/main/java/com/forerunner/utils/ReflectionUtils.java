package com.forerunner.utils;

import java.lang.reflect.Proxy;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.util.ClassUtils;

public class ReflectionUtils {

	public static Class<?> getTargetClass(Object o) {
		if (o instanceof HibernateProxy)
			return Hibernate.getClass(o);

		if (o instanceof SpringProxy)
			return AopProxyUtils.ultimateTargetClass(o);

		if (ClassUtils.isCglibProxy(o))
			return o.getClass().getSuperclass();

		if (Proxy.isProxyClass(o.getClass()))
			return o.getClass();

		return o.getClass();
	}
}
