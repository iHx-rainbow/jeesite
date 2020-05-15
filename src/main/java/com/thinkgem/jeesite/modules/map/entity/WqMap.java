/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.map.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 地图Entity
 * @author CaoYu
 * @version 2019-06-05
 */
public class WqMap extends DataEntity<WqMap> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 河流名称
	
	public WqMap() {
		super();
	}

	public WqMap(String id){
		super(id);
	}

	@Length(min=0, max=45, message="河流名称长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}