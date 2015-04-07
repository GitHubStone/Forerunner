package com.forerunner.core.persistent.interceptor;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 事件 模型
 * @author GQ
 */
public class FREntityEvent {
	private Serializable id;//唯一标识
	private Class<?> clazz;//相关模型
	private Map<String, Object> properties = Maps.newHashMap();//模型属性及属性值
	private String type;//事件类型

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public void putProperty(String propertyName, Object propertyValue) {
		this.properties.put(propertyName, propertyValue);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "{[" + type + " " + id + " " + clazz + "]" + super.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + "},\n";
	}
}