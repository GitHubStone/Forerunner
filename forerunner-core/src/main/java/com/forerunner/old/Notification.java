package com.forerunner.old;

import com.forerunner.core.persistent.BaseEntity;

public class Notification extends BaseEntity {
	private static final long serialVersionUID = -4660448257767739427L;

	private Long id;

	private String notifierType;
	private Long notifierId;

	private String receiverType;
	private Long receiverId;

	private Long messageId;

	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotifierType() {
		return notifierType;
	}

	public void setNotifierType(String notifierType) {
		this.notifierType = notifierType;
	}

	public Long getNotifierId() {
		return notifierId;
	}

	public void setNotifierId(Long notifierId) {
		this.notifierId = notifierId;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}