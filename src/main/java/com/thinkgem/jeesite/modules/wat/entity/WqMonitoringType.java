/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * typeEntity
 * @author hsj
 * @version 2019-05-06
 */
public class WqMonitoringType extends DataEntity<WqMonitoringType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String description;		// 监测点类型
	
	public WqMonitoringType() {
		super();
	}

	public WqMonitoringType(String id){
		super(id);
	}

	@Length(min=0, max=45, message="name长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="监测点类型长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}