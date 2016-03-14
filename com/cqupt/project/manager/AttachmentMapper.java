package com.cqupt.project.manager;



import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Attachment;

@Repository("attachmentDao")
public interface AttachmentMapper extends BaseDao<Attachment,String>{
	public ArrayList<Attachment> selectAttachments(String fileId);
	public int addAttachment(Attachment attachment);
	 
}
