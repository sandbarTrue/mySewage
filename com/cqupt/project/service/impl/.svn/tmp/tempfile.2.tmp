package com.cqupt.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.CompanyMapper;
import com.cqupt.project.manager.ProjectMapper;
import com.cqupt.project.manager.ScoreMapper;
import com.cqupt.project.model.Company;
import com.cqupt.project.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
@Resource
CompanyMapper companyDao;
@Override
public boolean addCompany(Company company){
	try{
		companyDao.insert(company);
	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;
}
//杨青
@Resource
ScoreMapper scoreDao;
@Resource 
ProjectMapper projectDao;
@Override
public ArrayList<Company> getSomeCompanies(int num) {
	num = (num < 0) ? 0 : num;
	if(num > companyDao.selectTotalNum()){
		return companyDao.getAllCompanies();
	}
	else{
		return companyDao.selectSomeCompanies(num);
	}
}

@Override
public ArrayList<Company> getAllCompanies(String order, int pageNow,int pageSize) {
	pageSize = (pageSize <= 0) ? 0 : pageSize;
	pageNow = (pageNow <= 0) ? 0: (pageNow - 1) * pageSize;
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("order", order);
	map.put("pageNow", pageNow);
	map.put("pageSize", pageSize);
	return companyDao.selectAllCompanies(map);
}

@Override
public Company getCompanyDetail(Integer companyId) {
	return (Company)companyDao.selectByCompanyId(companyId);
}
@Override
public int loadTotalpages(int pageSize) {
	pageSize = (pageSize <= 0)?1:pageSize;
	return companyDao.selectTotalNum() / pageSize;
}

@Override
public double getCompanyScore(Integer companyId) {
	double score = 0;
	Integer projectId;
	int num = 0;
	ArrayList<Integer> projectIds = projectDao.selectProjectIds(companyId);
	Iterator<Integer> it = projectIds.iterator();
	while(it.hasNext()){
		projectId = it.next().intValue();
		score += scoreDao.getProjectScore(projectId);
		num ++;
	}
	if(num == 0){
		return 0;
	}
	else{
		return score / num;
	}
}

@Override
public Company getCompany(Integer companyId) {
	return companyDao.selectByPrimaryKey(companyId);
}

@Override
public int modifyCompany(Company company) {
	if (company != null){
		Integer companyId = company.getCompanyId();
		Company company2 = getCompany(companyId);
		if(company2 == null) {
			return 0;
		}
	}
	return companyDao.updateByPrimaryKey(company);
}

@Override
public int deleteCompany(Integer companyId) {
	return companyDao.deleteByPrimaryKey(companyId);
}

@Override
public int getCompanyIdByregistNum(long registNum) {
	return companyDao.getCompanyIdByregistNum(registNum);
}


}
