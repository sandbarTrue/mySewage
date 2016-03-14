package com.cqupt.project.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Project;

@Repository("projectDao")
public interface ProjectMapper extends BaseDao<Project,Integer>{
	
	//首页的搜索，依据输入的内容查找符合的project
	public ArrayList<Project> findProjects(String condition);
	
	//首页的广告
	public ArrayList<Project> getSomeProjects(int num);
	
	//需求页面的project 和首页的project，首页不用传参数
	public ArrayList<Project> getProjects(Map<String, Object> map);
	
	//查询总条数
	public int selectTotalNum();
	

	//杨青
	//根据companyId查询project表中的projectId
	public ArrayList<Integer> selectProjectIds(int companyId);
	
	//根据companyId查询project表中所有的project
	public ArrayList<Project> selectProjectsByCompanyId(Integer companyId);

	public ArrayList<Project> getCompanyTask(Integer companyId);

	//获取成功案例
	public List<Project> getSuccessProject(Map<String, Object> map);
	
	//获取公司成功案例
	public List<Project> getCompanySuccessProject(int CompanyId);
    
	public List<Project> getSomeSuccessProject(int num);
}