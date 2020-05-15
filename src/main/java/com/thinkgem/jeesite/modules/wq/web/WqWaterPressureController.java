/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wq.web;

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
import com.thinkgem.jeesite.modules.wq.entity.WqWaterPressure;
import com.thinkgem.jeesite.modules.wq.service.WqWaterPressureService;

/**
 * 水压站点表Controller
 * @author kanouakira
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/wq/wqWaterPressure")
public class WqWaterPressureController extends BaseController {

	@Autowired
	private WqWaterPressureService wqWaterPressureService;
	
	@ModelAttribute
	public WqWaterPressure get(@RequestParam(required=false) String id) {
		WqWaterPressure entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqWaterPressureService.get(id);
		}
		if (entity == null){
			entity = new WqWaterPressure();
		}
		return entity;
	}
	
	@RequiresPermissions("wq:wqWaterPressure:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqWaterPressure wqWaterPressure, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqWaterPressure> page = wqWaterPressureService.findPage(new Page<WqWaterPressure>(request, response), wqWaterPressure); 
		model.addAttribute("page", page);
		return "modules/wq/wqWaterPressureList";
	}

	@RequiresPermissions("wq:wqWaterPressure:view")
	@RequestMapping(value = "form")
	public String form(WqWaterPressure wqWaterPressure, Model model) {
		model.addAttribute("wqWaterPressure", wqWaterPressure);
		return "modules/wq/wqWaterPressureForm";
	}

	@RequiresPermissions("wq:wqWaterPressure:edit")
	@RequestMapping(value = "save")
	public String save(WqWaterPressure wqWaterPressure, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqWaterPressure)){
			return form(wqWaterPressure, model);
		}
		wqWaterPressureService.save(wqWaterPressure);
		addMessage(redirectAttributes, "保存水压站点成功");
		return "redirect:"+Global.getAdminPath()+"/wq/wqWaterPressure/?repage";
	}
	
	@RequiresPermissions("wq:wqWaterPressure:edit")
	@RequestMapping(value = "delete")
	public String delete(WqWaterPressure wqWaterPressure, RedirectAttributes redirectAttributes) {
		wqWaterPressureService.delete(wqWaterPressure);
		addMessage(redirectAttributes, "删除水压站点成功");
		return "redirect:"+Global.getAdminPath()+"/wq/wqWaterPressure/?repage";
	}

}