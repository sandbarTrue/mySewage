package com.cqupt.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.model.Supervisor;
import com.cqupt.project.service.SupervisorService;
import com.cqupt.project.utils.JSONUtils;

@Controller("SupervisorController")
public class SupervisorController {
	@Resource
	private SupervisorService supervisorService;
	//查询公司简介
	@RequestMapping("/getSupervisorIntro")
	public void getSupervisorIntro(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String supervisorIntro = supervisorService.getSupervisorIntro();
		if(supervisorIntro != null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询成功");
			map.put("data", supervisorIntro);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "查询失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	//查询企业证书
	@RequestMapping("/getSupervisorCertificate")
	public void getSupervisorCertificate(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String supervisorCertificate = supervisorService.getSupervisorCertificate();
		if(supervisorCertificate != null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询成功");
			map.put("data", supervisorCertificate);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "查询失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	//查询工商注册信息
	@RequestMapping("/getRegistInfo")
	public void getRegistInfo(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		Long registInfo = supervisorService.getRegistInfo();
		if(registInfo != 0){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询成功");
			map.put("data", registInfo);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "查询失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	//查询广告详情
	@RequestMapping("/getAd")
	public void getAd(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String ad = supervisorService.getAd();
		if(ad == null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询成功");
			map.put("data", ad);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "查询失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	//查询联系我们
	@RequestMapping("/getPhoneNum")
	public void getPhoneNum(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String phoneNum = supervisorService.getPhoneNum();
		if(phoneNum == null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询成功");
			map.put("data", phoneNum);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "查询失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	//添加信息
	@RequestMapping("/AddSupervisor")
	public void AddSupervisor(Supervisor supervisor, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = supervisorService.AddSupervisor(supervisor);
		if(result != 0){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "添加成功");
			map.put("data", null);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "添加失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	public void getSupervisor(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		Supervisor supervisor = supervisorService.getSupervisor();
		if(supervisor != null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "修改成功");
			map.put("data", supervisor);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "修改失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	//修改信息
	@RequestMapping("/modifySupervisor")
	public void modifySupervisor(Supervisor supervisor, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = supervisorService.modifySupervisor(supervisor);
		if(result != 0){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "修改成功");
			map.put("data", null);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "修改失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
	//删除信息
	@RequestMapping("/deleteSupervisor")
	public void deleteSupervisor(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = supervisorService.deleteSupervisor();
		if(result != 0){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "删除成功");
			map.put("data", null);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "删除失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
}
