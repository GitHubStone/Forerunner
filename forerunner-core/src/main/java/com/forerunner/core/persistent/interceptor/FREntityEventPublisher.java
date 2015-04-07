package com.forerunner.core.persistent.interceptor;

/**
 * Portal项目事件发布者
 * @author GQ
 */
public interface FREntityEventPublisher {
	public void publishEvent(FREntityEvent event);
}
