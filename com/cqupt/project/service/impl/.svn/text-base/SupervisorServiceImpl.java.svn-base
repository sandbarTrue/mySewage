package com.cqupt.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.SupervisorMapper;
import com.cqupt.project.model.Supervisor;
import com.cqupt.project.service.SupervisorService;
@Service("SupervisorService")
public class SupervisorServiceImpl implements SupervisorService {
	@Resource
	private SupervisorMapper supervisorDao;
	@Override
	public String getSupervisorIntro() {
		return supervisorDao.getSupervisorIntro();
	}

	@Override
	public String getSupervisorCertificate() {
		return supervisorDao.getSupervisorCertificate();
	}

	@Override
	public Long getRegistInfo() {
		Long registInfo = supervisorDao.getRegistInfo();
		if(registInfo == null){
			return (long) 0;
		}
		else{
			return registInfo;
		}
	}

	@Override
	public String getAd() {
		return supervisorDao.getAd();
	}

	@Override
	public String getPhoneNum() {
		return supervisorDao.getPhoneNum();
	}

	@Override
	public int AddSupervisor(Supervisor supervisor) {
		if(supervisor == null)
			return 0;
		return supervisorDao.insert(supervisor);
	}

	@Override
	public int deleteSupervisor() {
		return supervisorDao.deleteSupervisor();
	}

	@Override
	public int modifySupervisor(Supervisor supervisor) {
		return supervisorDao.updateByPrimaryKey(supervisor);
	}

	@Override
	public Supervisor getSupervisor() {
		return supervisorDao.getSupervisor();
	}

}
