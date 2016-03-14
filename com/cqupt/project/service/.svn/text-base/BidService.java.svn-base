package com.cqupt.project.service;

import java.util.ArrayList;

import com.cqupt.project.model.Bid;



public interface  BidService {
	//增加一个标书
	public int addBid(Bid b);
	//删除一个标书
	public int deleteBid(int  id);
	//修改标书详情
	public int modifyBidDetail(int bidId,String  bidDetail);
	
	/*public int modifyBidFile(Bid b);*/
	//查询项目的竞标人数
	public int loadBidNum(int proId);
	
	//需求详情页面的已参与投标公司简介
	public ArrayList<Bid> loadByProjectId(int proId);
	
	//获取用户的标书
	public ArrayList<Bid> loadByCompanyId(int companyId);
	
   //获取标书
	public ArrayList<Bid> loadByProjectId(int proId,int pageNow,int pageSize);
}
