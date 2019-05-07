/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wqr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wqr.entity.WqQualityRecord;
import com.thinkgem.jeesite.modules.wqr.dao.WqQualityRecordDao;

/**
 * 水质检测Service
 * @author CaoYu
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class WqQualityRecordService extends CrudService<WqQualityRecordDao, WqQualityRecord> {

	public WqQualityRecord get(String id) {
		return super.get(id);
	}
	
	public List<WqQualityRecord> findList(WqQualityRecord wqQualityRecord) {
		return super.findList(wqQualityRecord);
	}
	
	public Page<WqQualityRecord> findPage(Page<WqQualityRecord> page, WqQualityRecord wqQualityRecord) {
		return super.findPage(page, wqQualityRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(WqQualityRecord wqQualityRecord) {
		super.save(wqQualityRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqQualityRecord wqQualityRecord) {
		super.delete(wqQualityRecord);
	}
	
}