package com.cqupt.project.manager;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Supervisor;
@Repository("supervisorDao")
public interface SupervisorMapper extends BaseDao<Supervisor,String>{	
	//查询公司简介
	public String getSupervisorIntro();
		
	//查询企业证书
	public String getSupervisorCertificate();
		
	//查询工商注册信息
	public Long getRegistInfo();
		
	//查询广告详情
	public String getAd();
		
	//查询联系我们
	public String getPhoneNum();

	//删除信息
	public int deleteSupervisor();
	
	public Supervisor getSupervisor();
}