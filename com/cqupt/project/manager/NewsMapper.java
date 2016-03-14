package com.cqupt.project.manager;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.News;
@Repository("newsDao")
public interface NewsMapper extends BaseDao<News,Integer>{
	public	int updateByPrimaryKeySelective(News n);
}