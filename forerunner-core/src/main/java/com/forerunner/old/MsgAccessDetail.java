package com.forerunner.old;

import java.util.Date;

import com.forerunner.core.persistent.BaseEntity;

public class MsgAccessDetail extends BaseEntity {
	private static final long serialVersionUID = 8501463876974309178L;

	private Long id;

	private String ip;
	private String clientInfo;
	private String location;

	private Date sentDate;//消息发出时间
	private Date receiptedDate;//消息被接收时间
	private Date sysReceiptedDate;//系统接收时间
	private Date sysSentDate;//系统发送时间
	private Long consumedTime; //消耗时间millisecond
	private Long sysConsumedTime; //系统消耗时间millisecond

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
}