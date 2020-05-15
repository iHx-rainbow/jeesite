/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.map.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.map.entity.WqMap;

/**
 * 地图DAO接口
 * @author CaoYu
 * @version 2019-06-05
 */
@MyBatisDao
public interface WqMapDao extends CrudDao<WqMap> {
	
}