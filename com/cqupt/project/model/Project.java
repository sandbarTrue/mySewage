package com.cqupt.project.model;

import java.util.ArrayList;
import java.util.Date;


public class Project {
	private Integer proId;

	private String proName;

	private Company company;
	private Integer companyId;

	private String briefIntro;// 项目简介

	private String proDetail;// 项目细节

	private Date proDeadline;// 项目截止日期

	private Date bidDeadline;// 投标截止日期

	private Long proBudget;// 项目预算

	private Integer proPhoneNum;// 项目负责人联系电话

	private ArrayList<Attachment> attachments;
	private String fileId;

	private String status;// 项目状态
	
	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}

	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public Date getBidDeadline() {
		return bidDeadline;
	}

	public String getBriefIntro() {
		return briefIntro;
	}

	public Company getCompany() {
		return company;
	}

	public Long getProBudget() {
		return proBudget;
	}

	public Date getProDeadline() {
		return proDeadline;
	}

	public String getProDetail() {
		return proDetail;
	}

	public Integer getProId() {
		return proId;
	}

	public String getProName() {
		return proName;
	}

	public Integer getProPhoneNum() {
		return proPhoneNum;
	}

	public String getStatus() {
		return status;
	}

	public void setBidDeadline(Date bidDeadline) {
		this.bidDeadline = bidDeadline;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setProBudget(Long proBudget) {
		this.proBudget = proBudget;
	}

	public void setProDeadline(Date proDeadline) {
		this.proDeadline = proDeadline;
	}

	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setProPhoneNum(Integer proPhoneNum) {
		this.proPhoneNum = proPhoneNum;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}