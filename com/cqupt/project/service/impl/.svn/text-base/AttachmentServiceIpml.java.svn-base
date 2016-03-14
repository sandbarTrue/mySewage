package com.cqupt.project.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cqupt.project.manager.AttachmentMapper;
import com.cqupt.project.model.Attachment;
import com.cqupt.project.service.AttachmentService;
@Service("attachmentService")
public class AttachmentServiceIpml implements AttachmentService{
	@Resource
	private AttachmentMapper attachmentDao;
	@Override
	public boolean addAttachment(CommonsMultipartFile[] files, String FileId,
			String realPath) {
		 for(int i = 0;i<files.length;i++){    
	            if(!files[i].isEmpty()){ 
	            	  Long number=new Date().getTime();
	            	  //设置存储名字
	                  String fileName=number.toString()+"upload";
	                  String realName=files[i].getOriginalFilename();
	                  System.out.println(fileName);
	                try {  
	                    //拿到输出流，同时重命名上传的文件  
	                	File file=new File(realPath);
	                	if(!file.isDirectory())
	                		file.mkdir();
	                    File localFile=new File(realPath+fileName);
	                	files[i].transferTo(localFile);
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                    System.out.println("上传出错");  
	                    return false;
	                    
	                }  
	                //把附件存到数据库里
	                System.out.println("kj");
	                Attachment file=new Attachment();
	                file.setFileId(FileId);
	                file.setFilePath(realPath);
	                file.setUploadFileName(fileName);
	                file.setUploadRealName(realName);
	                file.setTimeFileUpload(new Date());
	                try{
	                attachmentDao.insert(file);
	                
	                }
	                catch(Exception e){
	                	e.printStackTrace();
	                	System.out.println("bv");
	                	return false;
	                }
	            }
	            
	            }
		return true;
	}

	@Override
	public boolean deleteAttachemnt(String fileId) {
		try{
		 attachmentDao.deleteByPrimaryKey(fileId);
		}
		 catch(Exception e){
         	e.printStackTrace();
         	return false;
         }
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> LoadAttachments(String fileId) {
		if(fileId == " ") {
			return null;
		}
		List<Attachment> attachments = (List<Attachment>) attachmentDao.selectAttachments(fileId);
		return attachments;
	}

	
}
