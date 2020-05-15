/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.map.web;

import java.util.Arrays;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.map.entity.WqMap;
import com.thinkgem.jeesite.modules.map.service.WqMapService;

/**
 * 地图Controller
 * @author CaoYu
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/map/wqMap")
public class WqMapController extends BaseController {

	@Autowired
	private WqMapService wqMapService;
	
	@ModelAttribute
	public WqMap get(@RequestParam(required=false) String id) {
		WqMap entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wqMapService.get(id);
		}
		if (entity == null){
			entity = new WqMap();
		}
		return entity;
	}
	
	@RequiresPermissions("map:wqMap:view")
	@RequestMapping(value = {"list", ""})
	public String list(WqMap wqMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WqMap> page = wqMapService.findPage(new Page<WqMap>(request, response), wqMap); 
		model.addAttribute("page", page);
		
		
	    JSONObject object = new JSONObject();
	    List<String> leibie = Arrays.asList("水厂信息","水质站点","水压站点");
	    object.put("leibie",leibie);
	    
	    //string
	    JSONArray shuichanglist = new JSONArray();
	    JSONObject shuichangsite = new JSONObject();
	    shuichangsite.put("name","西湖");
	    shuichangsite.put("longitude","120.15");
	    shuichangsite.put("latitude","30.25");
	    shuichangsite.put("districtname","所属地区：杭州");
	    shuichanglist.add(shuichangsite);
	    
	    JSONArray shuizhilist = new JSONArray();
	    JSONObject shuizhisite1 = new JSONObject();
	    shuizhisite1.put("name","测试水质站点1");
	    shuizhisite1.put("longitude","120.16");
	    shuizhisite1.put("latitude","30.26");
	    shuizhisite1.put("districtname","所属地区：杭州");
	    JSONObject shuizhisite2 = new JSONObject();
	    shuizhisite2.put("name","测试水质站点2");
	    shuizhisite2.put("longitude","120.17");
	    shuizhisite2.put("latitude","30.27");
	    shuizhisite2.put("districtname","所属地区：杭州");
	    shuizhilist.add(shuizhisite1);
	    shuizhilist.add(shuizhisite2);
	    
	    JSONArray shuiyalist = new JSONArray();
	    JSONObject shuiyasite = new JSONObject();
	    shuiyasite.put("name","水压测试");
	    shuiyasite.put("longitude","120.20");
	    shuiyasite.put("latitude","30.25");
	    shuiyasite.put("districtname","所属地区：杭州");
	    shuiyalist.add(shuiyasite);
	    
	    
	    object.put("水厂信息",shuichanglist);
	    object.put("水质站点",shuizhilist);
	    object.put("水压站点",shuiyalist);
	    
	    System.out.println(object);
	    
	    /*
	    JSONObject shuizhi = new JSONObject();
	    shuizhi.put("amount",1);
	    //string
	    JSONArray shuizhilist = new JSONArray();
	    JSONObject shuizhisite = new JSONObject();
	    shuizhisite.put("name","测试水质站点");
	    shuizhisite.put("longitude","120.16");
	    shuizhisite.put("latitude","30.26");
	    shuizhisite.put("districtname","所属地区：杭州");
	    shuizhilist.add(shuizhisite);
	    shuizhi.put("location",shuizhilist);
	    System.out.println(shuizhi);
	    */
	    //String JSONobject = JSON.toJSONString(object);
	    
	    model.addAttribute("object", object);
	    /*
	    //int
	    object.put("int",2);
	    //boolean
	    object.put("boolean",true);
	    //array
	    List<Integer> integers = Arrays.asList(1,2,3);
	    object.put("list",integers);
	    //null
	    object.put("null",null);
		*/
		
		return "modules/map/wqMapList";
	}

	@RequiresPermissions("map:wqMap:view")
	@RequestMapping(value = "form")
	public String form(WqMap wqMap, Model model) {
		model.addAttribute("wqMap", wqMap);
		return "modules/map/wqMapForm";
	}

	@RequiresPermissions("map:wqMap:edit")
	@RequestMapping(value = "save")
	public String save(WqMap wqMap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wqMap)){
			return form(wqMap, model);
		}
		wqMapService.save(wqMap);
		addMessage(redirectAttributes, "保存地图成功");
		return "redirect:"+Global.getAdminPath()+"/map/wqMap/?repage";
	}
	
	@RequiresPermissions("map:wqMap:edit")
	@RequestMapping(value = "delete")
	public String delete(WqMap wqMap, RedirectAttributes redirectAttributes) {
		wqMapService.delete(wqMap);
		addMessage(redirectAttributes, "删除地图成功");
		return "redirect:"+Global.getAdminPath()+"/map/wqMap/?repage";
	}

}