package com.cqupt.project.controller;

import java.util.ArrayList;
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

import com.cqupt.project.model.Company;
import com.cqupt.project.model.Contract;
import com.cqupt.project.model.Project;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.service.CompanyService;
import com.cqupt.project.service.ContractService;
import com.cqupt.project.service.ProjectService;
import com.cqupt.project.utils.JSONUtils;

@Controller("companyController")
@RequestMapping("/company")
public class CompanyController {
	@Resource
	private CompanyService companyService;
	@Resource
	private ProjectService projectService;
	@Resource
	private AttachmentService attachmentService;
	@Resource
	private ContractService contractService;

	// 首页展示的公司
	@RequestMapping("/getSomeCompanies")
	public void getSomeCompanies(int num, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Company> companies = companyService.getSomeCompanies(num);
		if (companies != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", companies);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "无法找到该公司！");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 公司介绍页面
	@RequestMapping("/getAllCompanies")
	public void getAllCompanies(Integer companyId, String order, int pageNow,
			int pageSize, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Company> companies = companyService.getAllCompanies(order,
				pageNow, pageSize);
		// Integer companyId = (Integer) request.getAttribute("companyId");
		double score = companyService.getCompanyScore(companyId);
		int totalPages = companyService.loadTotalpages(pageSize);

		if (companies != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("score", score);
			data.put("totalPages", totalPages);
			data.put("companies", companies);
			map.put("data", data);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "无法找到该公司！");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 公司详情
	@RequestMapping("/getCompanyDetail")
	public void getCompanyDetail(Integer projectId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer companyId = (Integer) request.getAttribute("companyId");
		double score = companyService.getCompanyScore(companyId);
		Company company = companyService.getCompanyDetail(companyId);

		if (company != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("0", "0");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("company", company);
			data.put("score", score);
			map.put("data", data);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "无法找到该公司！");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 公司后台-->我的任务
	@RequestMapping("/getCompanyTask")
	public void getCompanyTask(Integer companyId,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Project> projects = projectService.getCompanyTask(companyId);
		if(projects != null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", projects);
		}
		else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 公司后台-->我的需求
	@RequestMapping("/getCompanyProject")
	public void getCompanyProject(HttpServletResponse response,
			Integer companyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Project> projects = projectService
				.selectProjectsByCompanyId(companyId);
		if (projects != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", projects);
		} else {
			map.put("status",ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);

	}

	// 公司后台-->添加项目
	@RequestMapping("/addProject")
	public void addProject(Project project, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile[] files) {
		Map<String, Object> map = new HashMap<String, Object>();
		String FileId = UUID.randomUUID().toString();
		project.setFileId(FileId);
		String realPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "uplode/";
		int result = projectService.addProject(project);
		boolean result2 = attachmentService.addAttachment(files, FileId,
				realPath);
		if (result == 1 && result2) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 公司后台-->我的合同
	@RequestMapping("/getCompanyContact")
	public void getCompanyContact(Integer companyId,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Contract> contracts = contractService
				.getContractByCompanyId(companyId);
		if (contracts != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询合同成功");
			map.put("data", contracts);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 公司后台-->个人中心(查询、删除、修改)
	@RequestMapping("/getCompany")
	public void getCompany(Integer companyId, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Company company = companyService.getCompany(companyId);
		if (company != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "查询成功");
			map.put("data", company);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "查询失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/modifyCompany")
	public void modifyCompany(Company company, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = companyService.modifyCompany(company);
		if (result == 1) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "修改成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "修改失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/deleteCompany")
	public void deleteCompany(Integer companyId, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = companyService.deleteCompany(companyId);
		if (result == 1) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}

		JSONUtils.toJSON(map, response);
	}
}