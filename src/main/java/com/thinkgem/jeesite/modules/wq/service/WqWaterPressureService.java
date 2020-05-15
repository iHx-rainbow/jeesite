/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wq.entity.WqWaterPressure;
import com.thinkgem.jeesite.modules.wq.dao.WqWaterPressureDao;

/**
 * 水压站点表Service
 * @author kanouakira
 * @version 2019-05-17
 */
@Service
@Transactional(readOnly = true)
public class WqWaterPressureService extends CrudService<WqWaterPressureDao, WqWaterPressure> {

	public WqWaterPressure get(String id) {
		return super.get(id);
	}
	
	public List<WqWaterPressure> findList(WqWaterPressure wqWaterPressure) {
		return super.findList(wqWaterPressure);
	}
	
	public Page<WqWaterPressure> findPage(Page<WqWaterPressure> page, WqWaterPressure wqWaterPressure) {
		return super.findPage(page, wqWaterPressure);
	}
	
	@Transactional(readOnly = false)
	public void save(WqWaterPressure wqWaterPressure) {
		super.save(wqWaterPressure);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqWaterPressure wqWaterPressure) {
		super.delete(wqWaterPressure);
	}
	
}