/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wrd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 水质检测Entity
 * @author CaoYu
 * @version 2019-05-06
 */
public class WqRecordType extends DataEntity<WqRecordType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 记录类型名称
	private String description;		// 类型简述
	
	public WqRecordType() {
		super();
	}

	public WqRecordType(String id){
		super(id);
	}

	@Length(min=0, max=45, message="记录类型名称长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="类型简述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}