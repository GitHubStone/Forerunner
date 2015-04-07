package com.forerunner.persistent.entity;

import java.io.Serializable;

/**
 * 实体基础类，统一实体唯一标示策略
 * @author GQ
 */
public abstract class BaseEntity implements Entity, Serializable {
	private static final long serialVersionUID = -7846944084655978173L;

	/**
	 * 统一使用ID标识唯一性
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {//是否引用地址相同
			return true;
		}
		if (obj == null || !(obj instanceof Entity)) {//对象非Forerunner实体或对象为空
			return false;
		}
		if (getId() == null) {//是否持久化实体
			return false;

		} else if (!getId().equals(((Entity) obj).getId())) {//是否id相同
			return false;
		}

		return true;
	}
}