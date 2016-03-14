package com.cqupt.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cqupt.project.model.Bid;
import com.cqupt.project.model.Project;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.service.BidService;
import com.cqupt.project.service.ProjectService;
import com.cqupt.project.utils.JSONUtils;

@Controller("projectController")
@RequestMapping("/project")
public class projectController {

	@Resource
	private ProjectService projectService;
	
	@Resource
	private BidService  bidService;
	
	@Resource
	private AttachmentService attachmentService;
	
	@RequestMapping("/addProject")
	public void insert(Project project, HttpServletRequest request, HttpServletResponse response, @RequestParam("file") CommonsMultipartFile[] files) {
		
		Map<String,Object> map  = new HashMap<String,Object>();
		 String FileId=UUID.randomUUID().toString();
		 project.setFileId(FileId);
		 String realPath=request.getSession().getServletContext().getRealPath("/")+"uplode/";
		 int result = projectService.addProject(project);
	     boolean result2 = attachmentService.addAttachment(files, FileId, realPath);
	        if(result == 1 && result2){
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
	//需求页面,order排序的关键字,pageNow 当前页数,pageSize 每页个数,
	@RequestMapping("/getProjects")
	public void getProjects(String order, int pageNow, int pageSize, HttpServletRequest request, HttpServletResponse response) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		Map <String,Object> data=new HashMap<String,Object>();
		ArrayList<Project> projects = projectService.loadAllProjects(order, pageNow, pageSize);
		int totalPages = projectService.loadTotalpages(pageSize);
		if(projects != null){
			int[] bidNums = new int[projects.size()];
			int i = 0;	
			for(Project project : projects) {
				System.out.println(project.getProId());
				bidNums[i] = bidService.loadBidNum(project.getProId());
				i++;
			}
    		map.put("status", ReturnStatus.SUCCSS);
			data.put("bidNums", bidNums);
			data.put("totalPages", totalPages);
			data.put("projects", projects);
			map.put("data", data);
			map.put("msg", "成功");
        }
    	else{
	    	map.put("status",ReturnStatus.ERROR);
	    	map.put("data", null);
			map.put("msg", "500");
       }
	JSONUtils.toJSON(map, response);
		
	}
	//首页的project 默认排序bidNum
	@RequestMapping("getIndexProjects")
	public void getIndexProject(int pageNow, int pageSize, HttpServletRequest request, HttpServletResponse response) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		Map <String, Object> data = new HashMap<String, Object>();
		List<Project> projects = projectService.loadAllProjects("bidNum", pageNow, pageSize);
		int totalPages = projectService.loadTotalpages(pageSize);
		if(projects != null){
			int[] bidNums = new int[projects.size()];
			int i = 0;
			for(Project project : projects) {
				bidNums[i] = bidService.loadBidNum(project.getProId());
				i++;
			}
    		map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			data.put("totalPages", totalPages);
			data.put("projects", projects);
			data.put("bidNums", bidNums);
			map.put("data",data);
        }else{
        	map.put("status",ReturnStatus.ERROR);
	    	map.put("msg","500");
	    	map.put("data",null);
        }
		JSONUtils.toJSON(map, response);

	}
	//广告
	@RequestMapping("/getSomeProjects")
	public void getSomeProjects(int num, HttpServletRequest request, HttpServletResponse response) {
		Map <String, Object> map = new HashMap<String, Object>(); 
		Map <String, Object> data = new HashMap<String, Object>(); 
		ArrayList<Project> projects = projectService.loadSomeProjects(num);
		int[] bidNum = new int[num];
		int i = 0;
		if(projects != null){
			for(Project project : projects) {
				bidNum[i] = bidService.loadBidNum(project.getProId());
				i++;
			}
    		map.put("status", ReturnStatus.SUCCSS);
			data.put("projects", projects);
			data.put("bidNums", bidNum);
			map.put("data", data);
			map.put("msg","成功");
        }
    	else{
	    	map.put("status",ReturnStatus.ERROR);
	    	map.put("msg","500");
	    	map.put("data",null);

       }
	   JSONUtils.toJSON(map, response);
	}
	//需求项目
	@RequestMapping("/projectShow")
	public void projectShow(int proId, HttpServletRequest request, HttpServletResponse response) {
		Map <String, Object> map = new HashMap<String, Object>(); 
		Map <String, Object> data = new HashMap<String, Object>(); 
		//Integer projectId = (Integer) request.getAttribute("proId");
		
		Project project = projectService.loadByPrimaryKey(proId);
		
		if(project.getProDeadline()==new Date()){
			project.setStatus("6");
		}
		int bidNum = bidService.loadBidNum(proId);
		ArrayList<Bid> bids = bidService.loadByProjectId(proId);
		if(project != null && bids != null){
    		map.put("status", ReturnStatus.SUCCSS);
			data.put("project", project);
			data.put("bids", bids);
			data.put("bidNum", bidNum);
			map.put("msg", "成功");
			map.put("data", data);
			
        }
    	else{
	    	map.put("status",ReturnStatus.ERROR);
	    	map.put("msg", "500");
	    	map.put("data",null);
       }
		
		JSONUtils.toJSON(map, response);
	}
	
	//查找需求
	@RequestMapping("/findProjects")
	public void findProjects(String condition, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Project> projects = projectService.loadProjects(condition);
		if (projects != null) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", projects);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}

		JSONUtils.toJSON(map, response);
	}
	//删除项目
	@RequestMapping("/deleteProject")
	public void deleteProject(int proId, HttpServletRequest request, HttpServletResponse response) {
		Map <String, Object> map = new HashMap<String, Object>(); 
		//Integer proId = (Integer) request.getAttribute("proId");
		int result = projectService.deleteProject(proId);
		if(result == 1){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", null);
        }
    	else{
    		map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
       }
		
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/updateProject")
	public void updateProject(Project project, HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("file") CommonsMultipartFile[] files) {
		Map<String, Object> map = new HashMap<String, Object>();
		String FileId = UUID.randomUUID().toString();
		String realPath = request.getSession().getServletContext()
				.getRealPath("/")+ "uplode/";
		int result = projectService.modifyProject(project);
		boolean result2 = attachmentService.addAttachment(files, FileId,
				realPath);
		if (result == 1 && result2) {
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
	@RequestMapping("/getSuccessProjects")
	public void getSuccessProjects(HttpServletResponse response,int pageNow,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Project> projects=projectService.loadSuccessProject(pageNow,pageSize);
		if(projects!=null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", projects);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);

	}
	@RequestMapping("/getSomeSuccessProjects")
	public void getSomeSuccessProjects(HttpServletResponse response,int num){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Project> projects=projectService.loadSomeSuccessProject(num);
		if(projects!=null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", projects);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);

	}
	@RequestMapping("/getCompanySuccessProjects")
	public void getCompanySuccessProjects(HttpServletResponse response,int companyId,int num){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Project> projects=projectService.loadCompanySuccessProject(companyId);
		if(projects!=null){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", projects);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	
}

