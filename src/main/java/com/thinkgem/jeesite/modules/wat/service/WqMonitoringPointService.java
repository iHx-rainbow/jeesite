/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wat.entity.WqMonitoringPoint;
import com.thinkgem.jeesite.modules.wat.dao.WqMonitoringPointDao;

/**
 * pointService
 * @author hsj
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class WqMonitoringPointService extends CrudService<WqMonitoringPointDao, WqMonitoringPoint> {

	public WqMonitoringPoint get(String id) {
		return super.get(id);
	}
	
	public List<WqMonitoringPoint> findList(WqMonitoringPoint wqMonitoringPoint) {
		return super.findList(wqMonitoringPoint);
	}
	
	public Page<WqMonitoringPoint> findPage(Page<WqMonitoringPoint> page, WqMonitoringPoint wqMonitoringPoint) {
		return super.findPage(page, wqMonitoringPoint);
	}
	
	@Transactional(readOnly = false)
	public void save(WqMonitoringPoint wqMonitoringPoint) {
		super.save(wqMonitoringPoint);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqMonitoringPoint wqMonitoringPoint) {
		super.delete(wqMonitoringPoint);
	}
	
}