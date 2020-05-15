/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wq.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 水源地站点表Entity
 * @author kanouakira
 * @version 2019-05-17
 */
public class WqWaterSource extends DataEntity<WqWaterSource> {
	
	private static final long serialVersionUID = 1L;
	private String zdName;		// 站点名称
	private String zdTypeid;		// 站点类型
	private String scId;		// 所属水厂
	private String deviceId;		// 设备ID
	private String pointLon;		// 站点经度
	private String pointLat;		// 站点纬度
	private String wsDataId;		// 水位数据ID
	private Double wsWarningMax;		// 预警上限值
	private Double wsWarningMin;		// 预警下限值
	private String picture;		// 图片路径
	
	public WqWaterSource() {
		super();
	}

	public WqWaterSource(String id){
		super(id);
	}

	@Length(min=1, max=64, message="站点名称长度必须介于 1 和 64 之间")
	public String getZdName() {
		return zdName;
	}

	public void setZdName(String zdName) {
		this.zdName = zdName;
	}
	
	@Length(min=1, max=11, message="站点类型长度必须介于 1 和 11 之间")
	public String getZdTypeid() {
		return zdTypeid;
	}

	public void setZdTypeid(String zdTypeid) {
		this.zdTypeid = zdTypeid;
	}
	
	@Length(min=1, max=11, message="所属水厂长度必须介于 1 和 11 之间")
	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}
	
	@Length(min=0, max=11, message="设备ID长度必须介于 0 和 11 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=45, message="站点经度长度必须介于 0 和 45 之间")
	public String getPointLon() {
		return pointLon;
	}

	public void setPointLon(String pointLon) {
		this.pointLon = pointLon;
	}
	
	@Length(min=0, max=45, message="站点纬度长度必须介于 0 和 45 之间")
	public String getPointLat() {
		return pointLat;
	}

	public void setPointLat(String pointLat) {
		this.pointLat = pointLat;
	}
	
	@Length(min=0, max=11, message="水位数据ID长度必须介于 0 和 11 之间")
	public String getWsDataId() {
		return wsDataId;
	}

	public void setWsDataId(String wsDataId) {
		this.wsDataId = wsDataId;
	}
	
	public Double getWsWarningMax() {
		return wsWarningMax;
	}

	public void setWsWarningMax(Double wsWarningMax) {
		this.wsWarningMax = wsWarningMax;
	}
	
	public Double getWsWarningMin() {
		return wsWarningMin;
	}

	public void setWsWarningMin(Double wsWarningMin) {
		this.wsWarningMin = wsWarningMin;
	}
	
	@Length(min=0, max=45, message="图片路径长度必须介于 0 和 45 之间")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}