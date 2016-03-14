package com.cqupt.project.manager;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Contract;

@Repository("contractDao")
public interface ContractMapper extends BaseDao<Contract,Integer>  {
	public ArrayList<Contract> getContractByCompanyId(Integer companyId);
	
}