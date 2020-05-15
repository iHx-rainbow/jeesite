/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wat.web;

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
import com.thinkgem.jeesite.modules.wat.entity.WqMonitoringPoint;
import com.thinkgem.jeesite.modules.wat.entity.WqMonitoringType;
import com.thinkgem.jeesite.modules.wat.service.WqMonitoringPointService;
import com.thinkgem.jeesite.modules.wat.service.WqMonitoringTypeService;

/**
 * pointController
 * @author hsj
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/wat/wqMonitoringPoint")
public class WqMonitoringPointController extends BaseController {

	@Autowired
	private WqMonitoringPointService wqMonitoringPointService;
	@Autowired
	private WqMonitoringTypeService wqMonitoringTypeService;
	
	@ModelAttribute
	public WqMonitoringPoint get(@RequestParam(required=false) String id) {
		WqMonitoringPoint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqMonitoringPointService.get(id);
		}
		if (entity == null){
			entity = new WqMonitoringPoint();
		}
		return entity;
	}
	
	@RequiresPermissions("wat:wqMonitoringPoint:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqMonitoringPoint wqMonitoringPoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqMonitoringPoint> page = wqMonitoringPointService.findPage(new Page<WqMonitoringPoint>(request, response), wqMonitoringPoint); 
		model.addAttribute("page", page);
		return "modules/wat/wqMonitoringPointList";
	}

	@RequiresPermissions("wat:wqMonitoringPoint:view")
	@RequestMapping(value = "form")
	public String form(WqMonitoringPoint wqMonitoringPoint, Model model) {
		List<WqMonitoringType> typeList = wqMonitoringTypeService.findList(new WqMonitoringType());
		model.addAttribute("typeList", typeList);
		model.addAttribute("wqMonitoringPoint", wqMonitoringPoint);
		return "modules/wat/wqMonitoringPointForm";
	}

	@RequiresPermissions("wat:wqMonitoringPoint:edit")
	@RequestMapping(value = "save")
	public String save(WqMonitoringPoint wqMonitoringPoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqMonitoringPoint)){
			return form(wqMonitoringPoint, model);
		}
		wqMonitoringPointService.save(wqMonitoringPoint);
		addMessage(redirectAttributes, "保存point成功");
		return "redirect:"+Global.getAdminPath()+"/wat/wqMonitoringPoint/?repage";
	}
	
	@RequiresPermissions("wat:wqMonitoringPoint:edit")
	@RequestMapping(value = "delete")
	public String delete(WqMonitoringPoint wqMonitoringPoint, RedirectAttributes redirectAttributes) {
		wqMonitoringPointService.delete(wqMonitoringPoint);
		addMessage(redirectAttributes, "删除point成功");
		return "redirect:"+Global.getAdminPath()+"/wat/wqMonitoringPoint/?repage";
	}

}