package com.cqupt.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.project.model.News;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.NewsService;
import com.cqupt.project.utils.JSONUtils;

@Controller("newsController")
@RequestMapping("/news")
public class NewsController {
	@Resource
	private NewsService newsService;
	@RequestMapping("/addNews")
	public void addNews(News n, HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		int result=newsService.addNews(n);
		if(result==1){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "插入成功");
			map.put("data", null);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	@RequestMapping("/delNews")
	public void delNews(HttpServletResponse response, int newsId){
		Map<String, Object> map = new HashMap<String, Object>();
		int result=newsService.deleteNews(newsId);
		if(result==1){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "删除成功");
			map.put("data", null);	
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	@RequestMapping("/modifyNews")
	public void modifyNews(HttpServletResponse response,News n){
		Map<String, Object> map = new HashMap<String, Object>();
		int result=newsService.modifyNews(n);
		if(result!=0){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "修改成功");
			map.put("data", null);		
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}
	@RequestMapping("/getNews")
	public void getNews(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		List<News> newsList=newsService.loadNews();
		if(newsList==null){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);	
		}
		else{
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", newsList);	
		}
		JSONUtils.toJSON(map, response);
	}
}
