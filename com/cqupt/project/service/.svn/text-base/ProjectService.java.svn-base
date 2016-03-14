package com.cqupt.project.service;

import java.util.ArrayList;





import java.util.List;

import com.cqupt.project.model.Project;

public interface ProjectService {

//	0,false 1,true
	int deleteProject(Integer proId);

    int addProject(Project project);

    //需求详情
    public Project loadByPrimaryKey(int proId);
    
    //首页的搜索
    public ArrayList<Project> loadProjects(String condition);
    
    //首页的广告
	public ArrayList<Project> loadSomeProjects(int num);
	
	//需求页面的project
	public ArrayList<Project> loadAllProjects(String order, int pageNow, int pageSize);
	
	//查询总页数
	public int loadTotalpages (int num);
	
    public int modifyProject(Project project);
    

    
    //杨青
  	//根据companyId查询project表中的projectId
    public ArrayList<Integer> selectProjectIds(Integer companyId);
    
	//根据companyId查询project表中的所有project
    public ArrayList<Project> selectProjectsByCompanyId(Integer companyId);
    
    public ArrayList<Project> getCompanyTask(Integer companyId);

    //成功案例
    public List<Project> loadSuccessProject(int pageNow, int pageSize);
    
    public List<Project> loadSomeSuccessProject(int num);
    
    //公司案例
    public List<Project> loadCompanySuccessProject(int CompanyId);

 
}
