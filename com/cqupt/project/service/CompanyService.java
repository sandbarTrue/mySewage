package com.cqupt.project.service;

import java.util.ArrayList;

import com.cqupt.project.model.Company;
import com.cqupt.project.model.Project;

public interface CompanyService {
public boolean addCompany(Company company);
//杨青

//首页展示的公司
public ArrayList<Company> getSomeCompanies(int num);

//公司介绍页面
public ArrayList<Company> getAllCompanies(String order, int pageNow, int pageSize);

//公司详情
public Company getCompanyDetail(Integer projectId);

//查询总页数
public int loadTotalpages (int num); 

//查询公司评分
public double getCompanyScore(Integer companyId);

//查询公司的个人中心
public Company getCompany(Integer companyId);

//修改公司的信息
public int modifyCompany(Company company);

//删除公司信息
public int deleteCompany(Integer companyId);

//根据公司名称查询公司Id
	public int getCompanyIdByregistNum(long registNum);

}