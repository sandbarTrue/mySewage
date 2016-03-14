package com.cqupt.project.manager;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Company;
import com.cqupt.project.model.Project;

@Repository("companyDao")
public interface CompanyMapper extends BaseDao<Company,Integer>{
	//杨青
	//根据查询companyId查询公司
	public Company selectByCompanyId(Integer companyId);
	
	//查询部分公司
	public ArrayList<Company> selectSomeCompanies(int num);
	
	//查询全部公司
	public ArrayList<Company> selectAllCompanies(Map<String, Object> map);
	
	//查询总条数
	public int selectTotalNum();
	
	//根据公司名称查询公司Id
	public int getCompanyIdByregistNum(long registNum);
	
	//根据工商注册号查询
	public Company selectByregistNum(long registNum);
	
	public ArrayList<Company> getAllCompanies();
}
