package com.cqupt.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.BidMapper;
import com.cqupt.project.model.Bid;
import com.cqupt.project.model.News;
import com.cqupt.project.service.BidService;

@Service("bidService")
public class BidServiceImpl implements BidService{

	@Resource
	private BidMapper bidDao;
	
	@Override
	public int loadBidNum(int proId) {
		if(loadByProjectId(proId) != null) {
			System.out.println(bidDao.selectBidNum(proId));
			return (int)bidDao.selectBidNum(proId);
		}
		else{
			return 0;
		}
		
	}

	@Override
	public ArrayList<Bid> loadByProjectId(int proId) {
		// TODO Auto-generated method stub、
			return  bidDao.selectByProjectId(proId);
	
	}

	@Override
	public int addBid(Bid b) {
		// TODO Auto-generated method stub
		if(b==null){
			return 0;
		}
		return bidDao.insert(b);
	}

	@Override
	public int deleteBid(int id) {
		// TODO Auto-generated method stub
		return bidDao.deleteByPrimaryKey(id);
	}

	@Override
	public int modifyBidDetail(int bidId,String bidDetail) {
		// TODO Auto-generated method stub
		   Bid b=bidDao.selectByPrimaryKey(bidId);
			if(b==null){
				return 0;
			}
	      Map<String,Object> map=new HashMap<String,Object>();
	      map.put("bidId", bidId);
	      map.put("bidDetail", bidDetail);
			
		return bidDao.updateBidDetail(map);
	}

/*	public int modifyBidFile(Bid b){}*/
	@Override
	public ArrayList<Bid> loadByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		
		return bidDao.selectByCompanyId(companyId);
	}

	@Override
	public ArrayList<Bid> loadByProjectId(int proId, int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		pageSize = (pageSize <= 0) ? 0 : pageSize;
		pageNow = (pageNow <= 0) ? 0 : (pageNow-1)*pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pageNow", pageNow);
		map.put("pageSize", pageSize);
		map.put("proId", proId);
		return bidDao.selectByProId(map);
	}

	
}
