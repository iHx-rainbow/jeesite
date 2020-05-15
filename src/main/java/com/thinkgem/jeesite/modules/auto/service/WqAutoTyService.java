/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.auto.entity.WqAutoTy;
import com.thinkgem.jeesite.modules.auto.dao.WqAutoTyDao;
import com.thinkgem.jeesite.modules.auto.entity.WqLogconfig;
import com.thinkgem.jeesite.modules.auto.dao.WqLogconfigDao;
import com.thinkgem.jeesite.modules.auto.entity.WqMonitoringConf;
import com.thinkgem.jeesite.modules.auto.dao.WqMonitoringConfDao;
import com.thinkgem.jeesite.modules.auto.entity.WqMonitoringYg;
import com.thinkgem.jeesite.modules.auto.dao.WqMonitoringYgDao;

/**
 * 自动投药Service
 * @author hsj
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly = true)
public class WqAutoTyService extends CrudService<WqAutoTyDao, WqAutoTy> {

	@Autowired
	private WqLogconfigDao wqLogconfigDao;
	@Autowired
	private WqMonitoringConfDao wqMonitoringConfDao;
	@Autowired
	private WqMonitoringYgDao wqMonitoringYgDao;
	
	public WqAutoTy get(String id) {
		WqAutoTy wqAutoTy = super.get(id);
		wqAutoTy.setWqLogconfigList(wqLogconfigDao.findList(new WqLogconfig(wqAutoTy)));
		wqAutoTy.setWqMonitoringConfList(wqMonitoringConfDao.findList(new WqMonitoringConf(wqAutoTy)));
		wqAutoTy.setWqMonitoringYgList(wqMonitoringYgDao.findList(new WqMonitoringYg(wqAutoTy)));
		return wqAutoTy;
	}
	
	public List<WqAutoTy> findList(WqAutoTy wqAutoTy) {
		return super.findList(wqAutoTy);
	}
	
	public Page<WqAutoTy> findPage(Page<WqAutoTy> page, WqAutoTy wqAutoTy) {
		return super.findPage(page, wqAutoTy);
	}
	
	@Transactional(readOnly = false)
	public void save(WqAutoTy wqAutoTy) {
		super.save(wqAutoTy);
		for (WqLogconfig wqLogconfig : wqAutoTy.getWqLogconfigList()){
			if (wqLogconfig.getId() == null){
				continue;
			}
			if (WqLogconfig.DEL_FLAG_NORMAL.equals(wqLogconfig.getDelFlag())){
				if (StringUtils.isBlank(wqLogconfig.getId())){
					wqLogconfig.setZdId(wqAutoTy);
					wqLogconfig.preInsert();
					wqLogconfigDao.insert(wqLogconfig);
				}else{
					wqLogconfig.preUpdate();
					wqLogconfigDao.update(wqLogconfig);
				}
			}else{
				wqLogconfigDao.delete(wqLogconfig);
			}
		}
		for (WqMonitoringConf wqMonitoringConf : wqAutoTy.getWqMonitoringConfList()){
			if (wqMonitoringConf.getId() == null){
				continue;
			}
			if (WqMonitoringConf.DEL_FLAG_NORMAL.equals(wqMonitoringConf.getDelFlag())){
				if (StringUtils.isBlank(wqMonitoringConf.getId())){
					wqMonitoringConf.setZdId(wqAutoTy);
					wqMonitoringConf.preInsert();
					wqMonitoringConfDao.insert(wqMonitoringConf);
				}else{
					wqMonitoringConf.preUpdate();
					wqMonitoringConfDao.update(wqMonitoringConf);
				}
			}else{
				wqMonitoringConfDao.delete(wqMonitoringConf);
			}
		}
		for (WqMonitoringYg wqMonitoringYg : wqAutoTy.getWqMonitoringYgList()){
			if (wqMonitoringYg.getId() == null){
				continue;
			}
			if (WqMonitoringYg.DEL_FLAG_NORMAL.equals(wqMonitoringYg.getDelFlag())){
				if (StringUtils.isBlank(wqMonitoringYg.getId())){
					wqMonitoringYg.setZdId(wqAutoTy);
					wqMonitoringYg.preInsert();
					wqMonitoringYgDao.insert(wqMonitoringYg);
				}else{
					wqMonitoringYg.preUpdate();
					wqMonitoringYgDao.update(wqMonitoringYg);
				}
			}else{
				wqMonitoringYgDao.delete(wqMonitoringYg);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WqAutoTy wqAutoTy) {
		super.delete(wqAutoTy);
		wqLogconfigDao.delete(new WqLogconfig(wqAutoTy));
		wqMonitoringConfDao.delete(new WqMonitoringConf(wqAutoTy));
		wqMonitoringYgDao.delete(new WqMonitoringYg(wqAutoTy));
	}
	
}