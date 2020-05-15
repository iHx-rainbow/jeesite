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
public class WqLogconfig extends DataEntity<WqLogconfig> {
	
	private static final long serialVersionUID = 1L;
	private WqAutoTy zdId;		// 站点id 父类
	private Integer sTime;		// 开始时间
	private Integer oTime;		// 结束时间
	private Double flow;		// 保持的流量
	
	public WqLogconfig() {
		super();
	}

	public WqLogconfig(String id){
		super(id);
	}

	public WqLogconfig(WqAutoTy zdId){
		this.zdId = zdId;
	}

	@Length(min=0, max=50, message="站点id长度必须介于 0 和 50 之间")
	public WqAutoTy getZdId() {
		return zdId;
	}

	public void setZdId(WqAutoTy zdId) {
		this.zdId = zdId;
	}
	
	public Integer getSTime() {
		return sTime;
	}

	public void setSTime(Integer sTime) {
		this.sTime = sTime;
	}
	
	public Integer getOTime() {
		return oTime;
	}

	public void setOTime(Integer oTime) {
		this.oTime = oTime;
	}
	
	public Double getFlow() {
		return flow;
	}

	public void setFlow(Double flow) {
		this.flow = flow;
	}
	
}