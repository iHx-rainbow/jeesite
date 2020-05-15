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
import com.thinkgem.jeesite.modules.wq.entity.WqWaterSource;
import com.thinkgem.jeesite.modules.wq.service.WqWaterSourceService;

/**
 * 水源地站点表Controller
 * @author kanouakira
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/wq/wqWaterSource")
public class WqWaterSourceController extends BaseController {

	@Autowired
	private WqWaterSourceService wqWaterSourceService;
	
	@ModelAttribute
	public WqWaterSource get(@RequestParam(required=false) String id) {
		WqWaterSource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqWaterSourceService.get(id);
		}
		if (entity == null){
			entity = new WqWaterSource();
		}
		return entity;
	}
	
	@RequiresPermissions("wq:wqWaterSource:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqWaterSource wqWaterSource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqWaterSource> page = wqWaterSourceService.findPage(new Page<WqWaterSource>(request, response), wqWaterSource); 
		model.addAttribute("page", page);
		return "modules/wq/wqWaterSourceList";
	}

	@RequiresPermissions("wq:wqWaterSource:view")
	@RequestMapping(value = "form")
	public String form(WqWaterSource wqWaterSource, Model model) {
		model.addAttribute("wqWaterSource", wqWaterSource);
		return "modules/wq/wqWaterSourceForm";
	}

	@RequiresPermissions("wq:wqWaterSource:edit")
	@RequestMapping(value = "save")
	public String save(WqWaterSource wqWaterSource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqWaterSource)){
			return form(wqWaterSource, model);
		}
		wqWaterSourceService.save(wqWaterSource);
		addMessage(redirectAttributes, "保存水源地站点成功");
		return "redirect:"+Global.getAdminPath()+"/wq/wqWaterSource/?repage";
	}
	
	@RequiresPermissions("wq:wqWaterSource:edit")
	@RequestMapping(value = "delete")
	public String delete(WqWaterSource wqWaterSource, RedirectAttributes redirectAttributes) {
		wqWaterSourceService.delete(wqWaterSource);
		addMessage(redirectAttributes, "删除水源地站点成功");
		return "redirect:"+Global.getAdminPath()+"/wq/wqWaterSource/?repage";
	}

}