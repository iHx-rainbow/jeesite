/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wrd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wrd.entity.WqRecordType;

/**
 * 水质检测DAO接口
 * @author CaoYu
 * @version 2019-05-06
 */
@MyBatisDao
public interface WqRecordTypeDao extends CrudDao<WqRecordType> {
	
}