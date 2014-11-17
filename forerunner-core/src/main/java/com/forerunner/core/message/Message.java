package com.forerunner.core.message;

import com.forerunner.core.target.Project;
import com.forerunner.persistent.entity.FRTraceEntity;

/**
 * 消息实体
 * @author GQ
 */
public class Message extends FRTraceEntity {
	private static final long serialVersionUID = -104343041110161125L;

	private Long id;
	private String infomation;//消息
	private String showTime;//消息发送时间
	private Project project;//详细规划

	private MsgTimeDetail timeDetail;//时间细节
	private MsgAccessDetail accessDetail;//访问细节

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfomation() {
		return infomation;
	}

	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public MsgTimeDetail getTimeDetail() {
		return timeDetail;
	}

	public void setTimeDetail(MsgTimeDetail timeDetail) {
		this.timeDetail = timeDetail;
	}

	public MsgAccessDetail getAccessDetail() {
		return accessDetail;
	}

	public void setAccessDetail(MsgAccessDetail accessDetail) {
		this.accessDetail = accessDetail;
	}
}