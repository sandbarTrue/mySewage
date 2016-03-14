package com.cqupt.project.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cqupt.project.model.Attachment;
public interface AttachmentService {
//上传多个文件
public boolean addAttachment(CommonsMultipartFile[] files, String FileId,
		String realPath);
//删除一个项目的附件
public boolean deleteAttachemnt(String fileId);

//拿到所有附件
public List<Attachment> LoadAttachments(String fileId);

 
}
