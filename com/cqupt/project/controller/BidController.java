package com.cqupt.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cqupt.project.model.Bid;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.service.BidService;
import com.cqupt.project.utils.JSONUtils;

@Controller("bidController")
@RequestMapping("/bid")
public class BidController {
	@Resource
	private BidService bidService;

	@Resource
	private AttachmentService attachmentService;
    @Resource
    private UserController userController;
	@RequestMapping("/addBid")
	public void addBid(Bid b, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile[] files) {
        boolean result1=userController.islogin(request);
       
		Map<String, Object> map = new HashMap<String, Object>();
		 if(!result1){
				map.put("status", ReturnStatus.ERROR);
				map.put("msg", "没有登录");
				map.put("data", null);
				JSONUtils.toJSON(map, response);
				return ;
			}
		String FileId = UUID.randomUUID().toString();
		b.setFileId(FileId);
		String realPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "upload/";
		int result = bidService.addBid(b);
		boolean result2 = attachmentService.addAttachment(files, FileId,
				realPath);
     
		if (result == 1 && result2 ) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "插入成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);

		}
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/delBid")
	public void delBid(HttpServletResponse response, int bidId,HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result1=userController.islogin(request);
		if(!result1){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "没有登录");
			map.put("data", null);
			JSONUtils.toJSON(map, response);
			return ;
		}
		int result = bidService.deleteBid(bidId);
		if (result == 1) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);

		}
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/modifyBidDetail")
	public void modifyBidDetail(HttpServletResponse response,int bidId,String bidDetail,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result1=userController.islogin(request);
		if(!result1){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "没有登录");
			map.put("data", null);
			JSONUtils.toJSON(map, response);
			return ;
		}
		int result = bidService.modifyBidDetail(bidId, bidDetail);
		if (result == 1 ) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "修改成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);

		}
		JSONUtils.toJSON(map, response);
	}
	@RequestMapping("/addBidFile")
	public void addBidFile(HttpServletResponse response,String fileId,@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean result1=userController.islogin(request);
		if(!result1){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "没有登录");
			map.put("data", null);
			JSONUtils.toJSON(map, response);
			return ;
		}
		String realPath = request.getSession().getServletContext().getRealPath("/")+ "uplode/";
		boolean result = attachmentService.addAttachment(files, fileId,realPath);
		if(result){
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "添加成功");
			map.put("data", null);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		
		JSONUtils.toJSON(map, response);
	}
/*
	@RequestMapping("/modifyBidFile")
	public void modifyBidFile(HttpServletResponse response,Bid b,
			@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除附件，添加附件或者直接添加附件
		String FileId = UUID.randomUUID().toString();
		String realPath = request.getSession().getServletContext().getRealPath("/")+ "uplode/";
		boolean result1=attachmentService.deleteAttachemnt(b.getFileId());
		boolean result2 = attachmentService.addAttachment(files, FileId,realPath);
		b.setFileId(FileId);
		int result = bidService.modifyBidFile(b);
		if (result == 1&&result1&&result2) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "修改成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);

		}
		JSONUtils.toJSON(map, response);
	}
*/
	@RequestMapping("/BidShow")
	public void BidShow(HttpServletResponse response,int proId,int pageNow,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Bid> bids=bidService.loadByProjectId(proId,pageNow,pageSize);
		if(bids.size()!=0){
			map.put("data", bids);
			map.put("msg", "成功");
			map.put("status", ReturnStatus.SUCCSS);
		}
		else{
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "500");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
		
	}
}

