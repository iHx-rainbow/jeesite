/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wq.entity.WqWaterQuality;
import com.thinkgem.jeesite.modules.wq.dao.WqWaterQualityDao;

/**
 * 水质站点表Service
 * @author kanouakira
 * @version 2019-05-17
 */
@Service
@Transactional(readOnly = true)
public class WqWaterQualityService extends CrudService<WqWaterQualityDao, WqWaterQuality> {

	public WqWaterQuality get(String id) {
		return super.get(id);
	}
	
	public List<WqWaterQuality> findList(WqWaterQuality wqWaterQuality) {
		return super.findList(wqWaterQuality);
	}
	
	public Page<WqWaterQuality> findPage(Page<WqWaterQuality> page, WqWaterQuality wqWaterQuality) {
		return super.findPage(page, wqWaterQuality);
	}
	
	@Transactional(readOnly = false)
	public void save(WqWaterQuality wqWaterQuality) {
		super.save(wqWaterQuality);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqWaterQuality wqWaterQuality) {
		super.delete(wqWaterQuality);
	}
	
}