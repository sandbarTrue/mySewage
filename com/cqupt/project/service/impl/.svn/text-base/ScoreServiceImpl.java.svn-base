package com.cqupt.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.ScoreMapper;
import com.cqupt.project.service.ScoreService;
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

	@Resource
	private ScoreMapper scoreDao;

	@Override
	public double getProjectScore(Integer projectId) {
		return scoreDao.getProjectScore(projectId);
	}
	
	

}
