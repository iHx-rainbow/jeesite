/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.map.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.map.entity.WqMap;
import com.thinkgem.jeesite.modules.map.dao.WqMapDao;

/**
 * 地图Service
 * @author CaoYu
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class WqMapService extends CrudService<WqMapDao, WqMap> {

	public WqMap get(String id) {
		return super.get(id);
	}
	
	public List<WqMap> findList(WqMap wqMap) {
		return super.findList(wqMap);
	}
	
	public Page<WqMap> findPage(Page<WqMap> page, WqMap wqMap) {
		return super.findPage(page, wqMap);
	}
	
	@Transactional(readOnly = false)
	public void save(WqMap wqMap) {
		super.save(wqMap);
	}
	
	@Transactional(readOnly = false)
	public void delete(WqMap wqMap) {
		super.delete(wqMap);
	}
	
}