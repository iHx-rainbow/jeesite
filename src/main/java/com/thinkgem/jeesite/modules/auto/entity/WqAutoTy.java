/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auto.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 自动投药Entity
 * @author hsj
 * @version 2019-05-16
 */
public class WqAutoTy extends DataEntity<WqAutoTy> {
	
	private static final long serialVersionUID = 1L;
	private String zdName;		// 站点名称
	private Integer zdTypeid;		// 站点类型id
	private Integer scId;		// 所属水厂id
	private Integer deviceId;		// 设备id
	private String picture;		// 站点图片
	private List<WqLogconfig> wqLogconfigList = Lists.newArrayList();		// 子表列表
	private List<WqMonitoringConf> wqMonitoringConfList = Lists.newArrayList();		// 子表列表
	private List<WqMonitoringYg> wqMonitoringYgList = Lists.newArrayList();		// 子表列表
	
	public WqAutoTy() {
		super();
	}

	public WqAutoTy(String id){
		super(id);
	}

	@Length(min=1, max=50, message="站点名称长度必须介于 1 和 50 之间")
	public String getZdName() {
		return zdName;
	}

	public void setZdName(String zdName) {
		this.zdName = zdName;
	}
	
	public Integer getZdTypeid() {
		return zdTypeid;
	}

	public void setZdTypeid(Integer zdTypeid) {
		this.zdTypeid = zdTypeid;
	}
	
	public Integer getScId() {
		return scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}
	
	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public List<WqLogconfig> getWqLogconfigList() {
		return wqLogconfigList;
	}

	public void setWqLogconfigList(List<WqLogconfig> wqLogconfigList) {
		this.wqLogconfigList = wqLogconfigList;
	}
	public List<WqMonitoringConf> getWqMonitoringConfList() {
		return wqMonitoringConfList;
	}

	public void setWqMonitoringConfList(List<WqMonitoringConf> wqMonitoringConfList) {
		this.wqMonitoringConfList = wqMonitoringConfList;
	}
	public List<WqMonitoringYg> getWqMonitoringYgList() {
		return wqMonitoringYgList;
	}

	public void setWqMonitoringYgList(List<WqMonitoringYg> wqMonitoringYgList) {
		this.wqMonitoringYgList = wqMonitoringYgList;
	}
}