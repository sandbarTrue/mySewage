package com.cqupt.project.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.ContractMapper;
import com.cqupt.project.model.Attachment;
import com.cqupt.project.model.Contract;
import com.cqupt.project.model.Project;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.service.ContractService;
@Service("contractService")
public class ContractServiceImpl implements ContractService {

	@Resource
	private ContractMapper contractDao;
	@Resource
	private AttachmentService attachmentService;
	
	@Override
	public int deleteByPrimaryKey(Integer contractId) {
		// TODO Auto-generated method stub
		Contract contract = loadByPrimaryKey(contractId);
		
		if (contract != null) {
			if(contract.getFileId() != "") {
				ArrayList<Attachment> attachments = (ArrayList<Attachment>) attachmentService.LoadAttachments(contract.getFileId());
				if(attachments != null) {
					attachmentService.deleteAttachemnt(contract.getFileId());
				}
			}
			return contractDao.deleteByPrimaryKey(contractId);
			
		}
		return 0;
	}

	@Override
	public int addContract(Contract contract) {
		// TODO Auto-generated method stub
		if(contract == null){
			return 0;
		}
		return contractDao.insert(contract);
	}

	@Override
	public Contract loadByPrimaryKey(Integer contractId) {
		// TODO Auto-generated method stub
		if(contractId == null) {
			return null;
		}
		return contractDao.selectByPrimaryKey(contractId);
	}

	@Override
	public ArrayList<Contract> loadAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Contract>)contractDao.findAll();
	}

	@Override
	public int modifyContract(Contract contract) {
		// TODO Auto-generated method stub
		if (contract != null){
			int contractId = contract.getContractId();
			Contract contract2 = loadByPrimaryKey(contractId);
			if(contract2 == null) {
				return 0;
			}
		}
    	return contractDao.updateByPrimaryKey(contract);
	}
//杨青
	@Override
	public ArrayList<Contract> getContractByCompanyId(Integer companyId) {
		return contractDao.getContractByCompanyId(companyId);
	}

}
