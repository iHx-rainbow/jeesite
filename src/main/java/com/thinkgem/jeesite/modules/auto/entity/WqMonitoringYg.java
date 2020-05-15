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
public class WqMonitoringYg extends DataEntity<WqMonitoringYg> {
	
	private static final long serialVersionUID = 1L;
	private WqAutoTy zdId;		// 站点id 父类
	private String ygName;		// 药罐名称
	private Integer dataId;		// 数据id
	private Integer situtationId;		// 位置编号
	
	public WqMonitoringYg() {
		super();
	}

	public WqMonitoringYg(String id){
		super(id);
	}

	public WqMonitoringYg(WqAutoTy zdId){
		this.zdId = zdId;
	}

	@Length(min=0, max=50, message="站点id长度必须介于 0 和 50 之间")
	public WqAutoTy getZdId() {
		return zdId;
	}

	public void setZdId(WqAutoTy zdId) {
		this.zdId = zdId;
	}
	
	@Length(min=0, max=50, message="药罐名称长度必须介于 0 和 50 之间")
	public String getYgName() {
		return ygName;
	}

	public void setYgName(String ygName) {
		this.ygName = ygName;
	}
	
	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	
	public Integer getSitutationId() {
		return situtationId;
	}

	public void setSitutationId(Integer situtationId) {
		this.situtationId = situtationId;
	}
	
}