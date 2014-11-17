package com.forerunner.core.message;

import java.util.Date;

import com.forerunner.persistent.entity.FRBaseEntity;

public class MsgTimeDetail extends FRBaseEntity {
	private static final long serialVersionUID = 8501463876974309178L;

	private Long id;
	private Date sentDate;//消息发出时间
	private Date receiptedDate;//消息被接收时间
	private Date sysReceiptedDate;//系统接收时间
	private Date sysSentDate;//系统发送时间
	private Long consumedTime; //消耗时间millisecond
	private Long sysConsumedTime; //系统消耗时间millisecond

	private Message message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getReceiptedDate() {
		return receiptedDate;
	}

	public void setReceiptedDate(Date receiptedDate) {
		this.receiptedDate = receiptedDate;
	}

	public Date getSysReceiptedDate() {
		return sysReceiptedDate;
	}

	public void setSysReceiptedDate(Date sysReceiptedDate) {
		this.sysReceiptedDate = sysReceiptedDate;
	}

	public Date getSysSentDate() {
		return sysSentDate;
	}

	public void setSysSentDate(Date sysSentDate) {
		this.sysSentDate = sysSentDate;
	}

	public Long getConsumedTime() {
		return consumedTime;
	}

	public void setConsumedTime(Long consumedTime) {
		this.consumedTime = consumedTime;
	}

	public Long getSysConsumedTime() {
		return sysConsumedTime;
	}

	public void setSysConsumedTime(Long sysConsumedTime) {
		this.sysConsumedTime = sysConsumedTime;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}