package com.cqupt.project.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cqupt.project.model.Contract;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.service.ContractService;
import com.cqupt.project.utils.JSONUtils;

@Controller("contractController")
@RequestMapping("/contract")
public class ContractController {
	@Resource
	private ContractService contractService;
	
	@Resource
	private AttachmentService attachmentService;
	
	//生成合同
	@RequestMapping("/addContract")
	public void addContract(Contract contract, HttpServletRequest request, HttpServletResponse response, @RequestParam("file")CommonsMultipartFile[] files){
		 String FileId=UUID.randomUUID().toString();
		 contract.setFileId(FileId);
		 String realPath=request.getSession().getServletContext().getRealPath("/")+"uplode/";
		 int result = contractService.addContract(contract);
		 boolean result2 = attachmentService.addAttachment(files, FileId, realPath);
		 Map<String, Object> map = new HashMap<String, Object>();
	    if(result == 1 && result2) {
	    	map.put("status", ReturnStatus.SUCCSS);
	    	map.put("msg", "添加成功");
	    	map.put("data", null);
        }
    	else{
	    	map.put("status",ReturnStatus.ERROR);
	    	map.put("msg","500");
	    	map.put("data", null);
       }
	    JSONUtils.toJSON(map, response); 
	}
	
	//合同细节展示
	@RequestMapping("/contractShow")
	public void contractShow(int contractId, HttpServletRequest request, HttpServletResponse response){
		//int contractId = (Integer)request.getAttribute("contractId");
		Contract contract = contractService.loadByPrimaryKey(contractId);
		Map<String, Object> map = new HashMap<String, Object>();
	/*	Map<String, Object> data = new HashMap<String, Object>();*/
		if(contract != null) {
	    	map.put("status", ReturnStatus.SUCCSS);
	    	map.put("msg", "成功");
	    	/*data.put("contract", contract);*/
			map.put("data", contract);
        }
    	else{
	    	map.put("status",ReturnStatus.ERROR);
	    	map.put("msg","500");
	    	map.put("data", null);
        }
	    JSONUtils.toJSON(map, response); 
	}
  
	
	//更新合同
	@RequestMapping("/updateContract")
	public void updateContract(Contract contract, HttpServletRequest request, HttpServletResponse response) {
		int result = contractService.modifyContract(contract);
		Map<String, Object> map = new HashMap<String, Object>();
		if (result == 1) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg","成功");
	    	map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
	    	map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	//删除合同
	@RequestMapping("/deleteContract")
	public void deleteContract(int contractId,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		 
		System.out.println(contractId);
		int result = contractService.deleteByPrimaryKey(contractId);
		if(result == 1) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg","成功");
	    	map.put("data", null);
		}
		else{
	    	map.put("status",ReturnStatus.ERROR);
	    	map.put("msg","500");
	    	map.put("data", null);
        }
		JSONUtils.toJSON(map, response); 
	}
}
