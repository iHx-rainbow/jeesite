/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wqr.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 水质检测Entity
 * @author CaoYu
 * @version 2019-05-06
 */
public class WqQualityRecord extends DataEntity<WqQualityRecord> {
	
	private static final long serialVersionUID = 1L;
	private String pointId;		// 所属监测站id
	private Date collectTime;		// 记录采集时间
	private String cod;		// 检测项目COD
	private String doo;		// 检测项目DO
	private String ph;		// 检测项目ph值
	private String bod;		// 检测项目BOD值
	private String temperature;		// 检测项目水温
	private String nh3;		// 检测项目nh3
	private String type;		// 记录的类型
	
	public WqQualityRecord() {
		super();
	}

	public WqQualityRecord(String id){
		super(id);
	}

	@Length(min=1, max=11, message="所属监测站id长度必须介于 1 和 11 之间")
	public String getPointId() {
		return pointId;
	}

	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="记录采集时间不能为空")
	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public String getDoo() {
		return doo;
	}

	public void setDoo(String doo) {
		this.doo = doo;
	}
	
	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}
	
	public String getBod() {
		return bod;
	}

	public void setBod(String bod) {
		this.bod = bod;
	}
	
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Length(min=0, max=45, message="检测项目nh3长度必须介于 0 和 45 之间")
	public String getNh3() {
		return nh3;
	}

	public void setNh3(String nh3) {
		this.nh3 = nh3;
	}
	
	@Length(min=0, max=11, message="记录的类型长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}