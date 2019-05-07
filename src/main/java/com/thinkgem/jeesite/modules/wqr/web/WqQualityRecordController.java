/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wqr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wqr.entity.WqQualityRecord;
import com.thinkgem.jeesite.modules.wqr.service.WqQualityRecordService;
import com.thinkgem.jeesite.modules.wrd.entity.WqRecordType;
import com.thinkgem.jeesite.modules.wrd.service.WqRecordTypeService;

/**
 * 水质检测Controller
 * @author CaoYu
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/wqr/wqQualityRecord")
public class WqQualityRecordController extends BaseController {

	@Autowired
	private WqQualityRecordService wqQualityRecordService;
	@Autowired
	private WqRecordTypeService wqRecordTypeService;
	
	@ModelAttribute
	public WqQualityRecord get(@RequestParam(required=false) String id) {
		WqQualityRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqQualityRecordService.get(id);
		}
		if (entity == null){
			entity = new WqQualityRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("wqr:wqQualityRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqQualityRecord wqQualityRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqQualityRecord> page = wqQualityRecordService.findPage(new Page<WqQualityRecord>(request, response), wqQualityRecord); 
		model.addAttribute("page", page);
		
		WqRecordType wqRecordType = new WqRecordType();
		
		List<WqRecordType> wqRecordTypeList = wqRecordTypeService.findList(wqRecordType);
		/*
		for (WqRecordType wrt : wqRecordTypeList){
			String typeName = wrt.getName();
			typeList.add(typeName);
		}
		*/
		System.out.println(wqRecordTypeList);
		model.addAttribute("typelist", wqRecordTypeList);
		
		return "modules/wqr/wqQualityRecordList";
	}

	@RequiresPermissions("wqr:wqQualityRecord:view")
	@RequestMapping(value = "form")
	public String form(WqQualityRecord wqQualityRecord, Model model) {
		model.addAttribute("wqQualityRecord", wqQualityRecord);
		
		WqRecordType wqRecordType = new WqRecordType();
		
		List<WqRecordType> wqRecordTypeList = wqRecordTypeService.findList(wqRecordType);
		/*
		for (WqRecordType wrt : wqRecordTypeList){
			String typeName = wrt.getName();
			typeList.add(typeName);
		}
		*/
		System.out.println(wqRecordTypeList);
		model.addAttribute("typelist", wqRecordTypeList);
		
		return "modules/wqr/wqQualityRecordForm";
	}

	@RequiresPermissions("wqr:wqQualityRecord:edit")
	@RequestMapping(value = "save")
	public String save(WqQualityRecord wqQualityRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqQualityRecord)){
			return form(wqQualityRecord, model);
		}
		wqQualityRecordService.save(wqQualityRecord);
		addMessage(redirectAttributes, "保存水质检测记录成功");
		return "redirect:"+Global.getAdminPath()+"/wqr/wqQualityRecord/?repage";
	}
	
	@RequiresPermissions("wqr:wqQualityRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(WqQualityRecord wqQualityRecord, RedirectAttributes redirectAttributes) {
		wqQualityRecordService.delete(wqQualityRecord);
		addMessage(redirectAttributes, "删除水质检测记录成功");
		return "redirect:"+Global.getAdminPath()+"/wqr/wqQualityRecord/?repage";
	}

}