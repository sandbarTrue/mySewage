package com.cqupt.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.project.model.Engineering;
import com.cqupt.project.model.News;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.EngineeringService;
import com.cqupt.project.utils.JSONUtils;

@Controller("engineeringController")
@RequestMapping("/engineering")
public class EngineeringController {
	@Resource
	private EngineeringService engineeringService;
	@RequestMapping("/addEngineering")
	public void addNews(Engineering n, HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		int result=engineeringService.addEngineering(n);
		if(result==1){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "插入成功");
			map.put("data", null);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	@RequestMapping("/delEngineering")
	public void delNews(HttpServletResponse response, int id){
		Map<String, Object> map = new HashMap<String, Object>();
		int result=engineeringService.deleteEngineering(id);
		if(result==1){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "删除成功");
			map.put("data", null);	
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	@RequestMapping("/getEngineering")
	public void getNews(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Engineering> EngineeringList=engineeringService.loadEngineering();
		if(EngineeringList==null){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);	
		}
		else{
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", EngineeringList);	
		}
		JSONUtils.toJSON(map, response);
	}
	
}
