package com.cqupt.project.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.ProjectMapper;
import com.cqupt.project.model.Attachment;
import com.cqupt.project.model.Project;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectMapper projectDao;
	
	@Resource
	private AttachmentService attachmentService;
	
	@Override
	public int deleteProject(Integer proId){
		Project project = loadByPrimaryKey(proId);

		if (project != null) {
			ArrayList<Attachment> attachments = (ArrayList<Attachment>) attachmentService.LoadAttachments(project.getFileId());
			if(attachments != null) 
				attachmentService.deleteAttachemnt(project.getFileId());
				return projectDao.deleteByPrimaryKey(proId);
		}
		return 0;
	}
	
	@Override
	public int addProject(Project project){
    	if(project == null) {
    		return 0;
    	}
    	return projectDao.insert(project);
    }

	@Override
	public Project loadByPrimaryKey(int proId){
    	return (Project)projectDao.selectByPrimaryKey(proId);
    }

	@Override
	public int modifyProject(Project project){
		if (project != null){
			int proId = project.getProId();
			Project project2 = loadByPrimaryKey(proId);
			if(project2 == null) {
				return 0;
			}
		}
    	return projectDao.updateByPrimaryKey(project);
    }

	@Override
	public ArrayList<Project> loadProjects(String condition) {
		// TODO Auto-generated method stub
		return projectDao.findProjects(condition);
	}

	@Override
	public ArrayList<Project> loadSomeProjects(int num) {
		// TODO Auto-generated method stub
		num = (num <= 0)?0:num;
		return projectDao.getSomeProjects(num);
	}

	@Override
	public ArrayList<Project> loadAllProjects(String order, int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		pageSize = (pageSize <= 0) ? 0 : pageSize;
		pageNow = (pageNow <= 0) ? 0 : (pageNow-1)*pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", order);
		map.put("pageNow", pageNow);
		map.put("pageSize", pageSize);
		return projectDao.getProjects(map);
	}

	@Override
	public int loadTotalpages(int pageSize) {
		// TODO Auto-generated method stub
		pageSize = (pageSize <= 0)?1:pageSize;
		return projectDao.selectTotalNum() / pageSize ;
	}

	//杨青
	@Override
	public ArrayList<Integer> selectProjectIds(Integer companyId) {
		return projectDao.selectProjectIds(companyId);
	}

	@Override
	public ArrayList<Project> selectProjectsByCompanyId(Integer companyId) {
		return projectDao.selectProjectsByCompanyId(companyId);
	}

	@Override
	public ArrayList<Project> getCompanyTask(Integer companyId) {
		return projectDao.getCompanyTask(companyId);
	}
	@Override
	public List<Project> loadSuccessProject(int pageNow,int pageSize) {
		// TODO Auto-generated method stub
		pageSize = (pageSize <= 0) ? 0 : pageSize;
		pageNow = (pageNow <= 0) ? 0 : (pageNow-1)*pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNow", pageNow);
		map.put("pageSize", pageSize);
		return projectDao.getSuccessProject(map);
	}
	@Override
	public List<Project> loadCompanySuccessProject(int CompanyId) {
		// TODO Auto-generated method stub
		
		return projectDao.getCompanySuccessProject(CompanyId);
	}

	@Override
	public List<Project> loadSomeSuccessProject(int num) {
		// TODO Auto-generated method stub
		
		return projectDao.getCompanySuccessProject(num);
	}


}
