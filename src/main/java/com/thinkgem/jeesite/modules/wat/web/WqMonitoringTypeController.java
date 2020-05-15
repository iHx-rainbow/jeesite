/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.web;

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
import com.thinkgem.jeesite.modules.wat.entity.WqMonitoringType;
import com.thinkgem.jeesite.modules.wat.service.WqMonitoringTypeService;

/**
 * typeController
 * @author hsj
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/wat/wqMonitoringType")
public class WqMonitoringTypeController extends BaseController {

	@Autowired
	private WqMonitoringTypeService wqMonitoringTypeService;
	
	@ModelAttribute
	public WqMonitoringType get(@RequestParam(required=false) String id) {
		WqMonitoringType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqMonitoringTypeService.get(id);
		}
		if (entity == null){
			entity = new WqMonitoringType();
		}
		return entity;
	}
	
	@RequiresPermissions("wat:wqMonitoringType:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqMonitoringType wqMonitoringType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqMonitoringType> page = wqMonitoringTypeService.findPage(new Page<WqMonitoringType>(request, response), wqMonitoringType); 
		model.addAttribute("page", page);
		return "modules/wat/wqMonitoringTypeList";
	}

	@RequiresPermissions("wat:wqMonitoringType:view")
	@RequestMapping(value = "form")
	public String form(WqMonitoringType wqMonitoringType, Model model) {
		model.addAttribute("wqMonitoringType", wqMonitoringType);
		return "modules/wat/wqMonitoringTypeForm";
	}

	@RequiresPermissions("wat:wqMonitoringType:edit")
	@RequestMapping(value = "save")
	public String save(WqMonitoringType wqMonitoringType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqMonitoringType)){
			return form(wqMonitoringType, model);
		}
		wqMonitoringTypeService.save(wqMonitoringType);
		addMessage(redirectAttributes, "保存type成功");
		return "redirect:"+Global.getAdminPath()+"/wat/wqMonitoringType/?repage";
	}
	
	@RequiresPermissions("wat:wqMonitoringType:edit")
	@RequestMapping(value = "delete")
	public String delete(WqMonitoringType wqMonitoringType, RedirectAttributes redirectAttributes) {
		wqMonitoringTypeService.delete(wqMonitoringType);
		addMessage(redirectAttributes, "删除type成功");
		return "redirect:"+Global.getAdminPath()+"/wat/wqMonitoringType/?repage";
	}

}