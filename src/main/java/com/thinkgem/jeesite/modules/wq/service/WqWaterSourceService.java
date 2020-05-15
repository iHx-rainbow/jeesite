/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wq.entity.WqWaterSource;
import com.thinkgem.jeesite.modules.wq.dao.WqWaterSourceDao;

/**
 * 水源地站点表Service
 * @author kanouakira
 * @version 2019-05-17
 */
@Service
@Transactional(readOnly = true)
public class WqWaterSourceService extends CrudService<WqWaterSourceDao, WqWaterSource> {

	public WqWaterSource get(String id) {
		return super.get(id);
	}
	
	public List<WqWaterSource> findList(WqWaterSource wqWaterSource) {
		return super.findList(wqWaterSource);
	}
	
	public Page<WqWaterSource> findPage(Page<WqWaterSource> page, WqWaterSource wqWaterSource) {
		return super.findPage(page, wqWaterSource);
	}
	
	@Transactional(readOnly = false)
	public void save(WqWaterSource wqWaterSource) {
		super.save(wqWaterSource);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqWaterSource wqWaterSource) {
		super.delete(wqWaterSource);
	}
	
}