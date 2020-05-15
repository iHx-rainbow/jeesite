/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wq.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wq.entity.WqWaterQuality;

/**
 * 水质站点表DAO接口
 * @author kanouakira
 * @version 2019-05-17
 */
@MyBatisDao
public interface WqWaterQualityDao extends CrudDao<WqWaterQuality> {
	
}