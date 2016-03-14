package com.cqupt.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.NewsMapper;
import com.cqupt.project.model.News;
import com.cqupt.project.model.Project;
import com.cqupt.project.service.NewsService;
@Service("newsService")
public class NewsServiceIpml implements NewsService{
	@Resource
	private NewsMapper newsDao; 
	@Override
	public int addNews(News n) {
		// TODO Auto-generated method stub
		if(n==null){
			return 0;
		}
		return  newsDao.insert(n);
	}

	@Override
	public int deleteNews(int newsId) {
		// TODO Auto-generated method stub
		
		return newsDao.deleteByPrimaryKey(newsId);
	}

	@Override
	public int modifyNews(News n) {
		// TODO Auto-generated method stub
		if (n != null){
			int newsId = n.getNewsId();
			News news=newsDao.selectByPrimaryKey(newsId);
			if(news == null) {
				return 0;
			}
		}
		return newsDao.updateByPrimaryKeySelective(n);
	}

	@Override
	public List<News> loadNews() {
		// TODO Auto-generated method stub
		return newsDao.findAll();
	}

}
