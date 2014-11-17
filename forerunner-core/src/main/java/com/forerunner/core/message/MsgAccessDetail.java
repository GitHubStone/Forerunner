package com.forerunner.core.message;

import com.forerunner.persistent.entity.FRBaseEntity;

public class MsgAccessDetail extends FRBaseEntity {
	private static final long serialVersionUID = 8501463876974309178L;

	private Long id;

	private String ip;
	private String clientInfo;
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}