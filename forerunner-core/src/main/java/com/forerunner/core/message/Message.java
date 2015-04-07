package com.forerunner.core.message;

import com.forerunner.core.target.Project;
import com.forerunner.persistent.entity.Trace;

/**
 * <h3>消息实体</h3>
 * 
 * @author GUOQIANG
 */
public class Message extends Trace {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String infomation;//消息
	private String showTime;//消息查看时间
	private Project project;//详细规划

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

	public MsgAccessDetail getAccessDetail() {
		return accessDetail;
	}

	public void setAccessDetail(MsgAccessDetail accessDetail) {
		this.accessDetail = accessDetail;
	}
}