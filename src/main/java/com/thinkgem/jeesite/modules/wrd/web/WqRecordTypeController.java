/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wrd.web;

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
import com.thinkgem.jeesite.modules.wrd.entity.WqRecordType;
import com.thinkgem.jeesite.modules.wrd.service.WqRecordTypeService;

/**
 * 水质检测Controller
 * @author CaoYu
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/wrd/wqRecordType")
public class WqRecordTypeController extends BaseController {

	@Autowired
	private WqRecordTypeService wqRecordTypeService;
	
	@ModelAttribute
	public WqRecordType get(@RequestParam(required=false) String id) {
		WqRecordType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqRecordTypeService.get(id);
		}
		if (entity == null){
			entity = new WqRecordType();
		}
		return entity;
	}
	
	@RequiresPermissions("wrd:wqRecordType:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqRecordType wqRecordType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqRecordType> page = wqRecordTypeService.findPage(new Page<WqRecordType>(request, response), wqRecordType); 
		model.addAttribute("page", page);
		return "modules/wrd/wqRecordTypeList";
	}

	@RequiresPermissions("wrd:wqRecordType:view")
	@RequestMapping(value = "form")
	public String form(WqRecordType wqRecordType, Model model) {
		model.addAttribute("wqRecordType", wqRecordType);
		return "modules/wrd/wqRecordTypeForm";
	}

	@RequiresPermissions("wrd:wqRecordType:edit")
	@RequestMapping(value = "save")
	public String save(WqRecordType wqRecordType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqRecordType)){
			return form(wqRecordType, model);
		}
		wqRecordTypeService.save(wqRecordType);
		addMessage(redirectAttributes, "保存水质检测类型成功");
		return "redirect:"+Global.getAdminPath()+"/wrd/wqRecordType/?repage";
	}
	
	@RequiresPermissions("wrd:wqRecordType:edit")
	@RequestMapping(value = "delete")
	public String delete(WqRecordType wqRecordType, RedirectAttributes redirectAttributes) {
		wqRecordTypeService.delete(wqRecordType);
		addMessage(redirectAttributes, "删除水质检测类型成功");
		return "redirect:"+Global.getAdminPath()+"/wrd/wqRecordType/?repage";
	}

}