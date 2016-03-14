package com.cqupt.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.EngineeringMapper;
import com.cqupt.project.model.Engineering;
import com.cqupt.project.model.News;
import com.cqupt.project.service.EngineeringService;
@Service("engineeringService")
public class EngineeringServiceImpl implements EngineeringService{
	@Resource
	private EngineeringMapper engineeringDao;
	@Override
	public int addEngineering(Engineering n) {
		// TODO Auto-generated method stub
		
		return engineeringDao.insert(n);
	}

	@Override
	public int deleteEngineering(int id) {
		// TODO Auto-generated method stub
		return engineeringDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Engineering> loadEngineering() {
		// TODO Auto-generated method stub
		return engineeringDao.findAll();
				
	}

}
