/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auto.web;

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
import com.thinkgem.jeesite.modules.auto.entity.WqAutoTy;
import com.thinkgem.jeesite.modules.auto.service.WqAutoTyService;

/**
 * 自动投药Controller
 * @author hsj
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/auto/wqAutoTy")
public class WqAutoTyController extends BaseController {

	@Autowired
	private WqAutoTyService wqAutoTyService;
	
	@ModelAttribute
	public WqAutoTy get(@RequestParam(required=false) String id) {
		WqAutoTy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqAutoTyService.get(id);
		}
		if (entity == null){
			entity = new WqAutoTy();
		}
		return entity;
	}
	
	@RequiresPermissions("auto:wqAutoTy:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqAutoTy wqAutoTy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqAutoTy> page = wqAutoTyService.findPage(new Page<WqAutoTy>(request, response), wqAutoTy); 
		model.addAttribute("page", page);
		return "modules/auto/wqAutoTyList";
	}

	@RequiresPermissions("auto:wqAutoTy:view")
	@RequestMapping(value = "form")
	public String form(WqAutoTy wqAutoTy, Model model) {
		model.addAttribute("wqAutoTy", wqAutoTy);
		return "modules/auto/wqAutoTyForm";
	}

	@RequiresPermissions("auto:wqAutoTy:edit")
	@RequestMapping(value = "save")
	public String save(WqAutoTy wqAutoTy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqAutoTy)){
			return form(wqAutoTy, model);
		}
		wqAutoTyService.save(wqAutoTy);
		addMessage(redirectAttributes, "保存自动投药成功");
		return "redirect:"+Global.getAdminPath()+"/auto/wqAutoTy/?repage";
	}
	
	@RequiresPermissions("auto:wqAutoTy:edit")
	@RequestMapping(value = "delete")
	public String delete(WqAutoTy wqAutoTy, RedirectAttributes redirectAttributes) {
		wqAutoTyService.delete(wqAutoTy);
		addMessage(redirectAttributes, "删除自动投药成功");
		return "redirect:"+Global.getAdminPath()+"/auto/wqAutoTy/?repage";
	}

}