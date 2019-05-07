/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wrd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wrd.entity.WqRecordType;
import com.thinkgem.jeesite.modules.wrd.dao.WqRecordTypeDao;

/**
 * 水质检测Service
 * @author CaoYu
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class WqRecordTypeService extends CrudService<WqRecordTypeDao, WqRecordType> {

	public WqRecordType get(String id) {
		return super.get(id);
	}
	
	public List<WqRecordType> findList(WqRecordType wqRecordType) {
		return super.findList(wqRecordType);
	}
	
	public Page<WqRecordType> findPage(Page<WqRecordType> page, WqRecordType wqRecordType) {
		return super.findPage(page, wqRecordType);
	}
	
	@Transactional(readOnly = false)
	public void save(WqRecordType wqRecordType) {
		super.save(wqRecordType);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqRecordType wqRecordType) {
		super.delete(wqRecordType);
	}
	
}