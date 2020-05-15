/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wat.entity.WqMonitoringPoint;

/**
 * pointDAO接口
 * @author hsj
 * @version 2019-05-06
 */
@MyBatisDao
public interface WqMonitoringPointDao extends CrudDao<WqMonitoringPoint> {
	
}