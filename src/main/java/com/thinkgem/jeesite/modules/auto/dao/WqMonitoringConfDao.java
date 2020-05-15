/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auto.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.auto.entity.WqMonitoringConf;

/**
 * 自动投药DAO接口
 * @author hsj
 * @version 2019-05-16
 */
@MyBatisDao
public interface WqMonitoringConfDao extends CrudDao<WqMonitoringConf> {
	
}