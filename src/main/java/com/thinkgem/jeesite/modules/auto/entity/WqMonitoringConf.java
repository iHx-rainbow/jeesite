/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auto.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 自动投药Entity
 * @author hsj
 * @version 2019-05-16
 */
public class WqMonitoringConf extends DataEntity<WqMonitoringConf> {
	
	private static final long serialVersionUID = 1L;
	private WqAutoTy zdId;		// 站点id 父类
	private String valveName;		// 阀门名称
	private Integer flowdataId;		// 流量数据id
	
	public WqMonitoringConf() {
		super();
	}

	public WqMonitoringConf(String id){
		super(id);
	}

	public WqMonitoringConf(WqAutoTy zdId){
		this.zdId = zdId;
	}

	@Length(min=0, max=50, message="站点id长度必须介于 0 和 50 之间")
	public WqAutoTy getZdId() {
		return zdId;
	}

	public void setZdId(WqAutoTy zdId) {
		this.zdId = zdId;
	}
	
	@Length(min=0, max=50, message="阀门名称长度必须介于 0 和 50 之间")
	public String getValveName() {
		return valveName;
	}

	public void setValveName(String valveName) {
		this.valveName = valveName;
	}
	
	public Integer getFlowdataId() {
		return flowdataId;
	}

	public void setFlowdataId(Integer flowdataId) {
		this.flowdataId = flowdataId;
	}
	
}