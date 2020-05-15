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
import com.thinkgem.jeesite.modules.wq.entity.WqWaterQuality;
import com.thinkgem.jeesite.modules.wq.service.WqWaterQualityService;

/**
 * 水质站点表Controller
 * @author kanouakira
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/wq/wqWaterQuality")
public class WqWaterQualityController extends BaseController {

	@Autowired
	private WqWaterQualityService wqWaterQualityService;
	
	@ModelAttribute
	public WqWaterQuality get(@RequestParam(required=false) String id) {
		WqWaterQuality entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqWaterQualityService.get(id);
		}
		if (entity == null){
			entity = new WqWaterQuality();
		}
		return entity;
	}
	
	@RequiresPermissions("wq:wqWaterQuality:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqWaterQuality wqWaterQuality, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqWaterQuality> page = wqWaterQualityService.findPage(new Page<WqWaterQuality>(request, response), wqWaterQuality); 
		model.addAttribute("page", page);
		return "modules/wq/wqWaterQualityList";
	}

	@RequiresPermissions("wq:wqWaterQuality:view")
	@RequestMapping(value = "form")
	public String form(WqWaterQuality wqWaterQuality, Model model) {
		model.addAttribute("wqWaterQuality", wqWaterQuality);
		return "modules/wq/wqWaterQualityForm";
	}

	@RequiresPermissions("wq:wqWaterQuality:edit")
	@RequestMapping(value = "save")
	public String save(WqWaterQuality wqWaterQuality, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqWaterQuality)){
			return form(wqWaterQuality, model);
		}
		wqWaterQualityService.save(wqWaterQuality);
		addMessage(redirectAttributes, "保存水质站点成功");
		return "redirect:"+Global.getAdminPath()+"/wq/wqWaterQuality/?repage";
	}
	
	@RequiresPermissions("wq:wqWaterQuality:edit")
	@RequestMapping(value = "delete")
	public String delete(WqWaterQuality wqWaterQuality, RedirectAttributes redirectAttributes) {
		wqWaterQualityService.delete(wqWaterQuality);
		addMessage(redirectAttributes, "删除水质站点成功");
		return "redirect:"+Global.getAdminPath()+"/wq/wqWaterQuality/?repage";
	}

}