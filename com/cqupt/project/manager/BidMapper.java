package com.cqupt.project.manager;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Bid;

@Repository("bidDao")
public interface BidMapper extends BaseDao<Bid,Integer>{
	
	//查询项目的竞标人数
	public Integer selectBidNum(int proId);
	
	//需求详情页面的已投标公司
	public ArrayList<Bid> selectByProjectId(int proId);
	
	//更新标书详情
	public int updateBidDetail(Map<String, Object> map);
	//更新附件
	public int updateFile(int id,String fileId);
	//公司的标书
	public ArrayList<Bid> selectByCompanyId(int companyId);
	
	//标书要分页
	public ArrayList<Bid> selectByProId(Map<String, Object> map);
}