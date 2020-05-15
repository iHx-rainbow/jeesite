/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * pointEntity
 * @author hsj
 * @version 2019-05-06
 */
public class WqMonitoringPoint extends DataEntity<WqMonitoringPoint> {
	
	private static final long serialVersionUID = 1L;
	private Integer typeId;		// type_id
	private String name;		// 监测点名称
	private String description;		// 描述
	private Integer villageId;		// 所属街道/村
	private String riverId;		// 所在河流id
	private Double longitude;		// 地理经度
	private Double latitude;		// 地理纬度
	private String images;		// 站点图片存储路径，多图的话用分号分割
	private WqMonitoringType wqMonitoringType;  //多表查询，查询typeid对应的type
	
	public WqMonitoringType getWqMonitoringType() {
		return wqMonitoringType;
	}

	public void setWqMonitoringType(WqMonitoringType wqMonitoringType) {
		this.wqMonitoringType = wqMonitoringType;
	}

	public WqMonitoringPoint() {
		super();
	}

	public WqMonitoringPoint(String id){
		super(id);
	}

	@NotNull(message="type_id不能为空")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=0, max=255, message="监测点名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	
	@Length(min=0, max=45, message="所在河流id长度必须介于 0 和 45 之间")
	public String getRiverId() {
		return riverId;
	}

	public void setRiverId(String riverId) {
		this.riverId = riverId;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}