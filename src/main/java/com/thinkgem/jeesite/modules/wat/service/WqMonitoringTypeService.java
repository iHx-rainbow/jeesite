/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wat.entity.WqMonitoringType;
import com.thinkgem.jeesite.modules.wat.dao.WqMonitoringTypeDao;

/**
 * typeService
 * @author hsj
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class WqMonitoringTypeService extends CrudService<WqMonitoringTypeDao, WqMonitoringType> {

	public WqMonitoringType get(String id) {
		return super.get(id);
	}
	
	public List<WqMonitoringType> findList(WqMonitoringType wqMonitoringType) {
		return super.findList(wqMonitoringType);
	}
	
	public Page<WqMonitoringType> findPage(Page<WqMonitoringType> page, WqMonitoringType wqMonitoringType) {
		return super.findPage(page, wqMonitoringType);
	}
	
	@Transactional(readOnly = false)
	public void save(WqMonitoringType wqMonitoringType) {
		super.save(wqMonitoringType);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqMonitoringType wqMonitoringType) {
		super.delete(wqMonitoringType);
	}
	
}