package com.cqupt.project.service;

import java.util.ArrayList;

import com.cqupt.project.model.Contract;

public  interface ContractService {
	
	int deleteByPrimaryKey(Integer contractId);

    int addContract(Contract contract);
    
    int modifyContract(Contract contract);
     
    Contract loadByPrimaryKey(Integer contractId);
    
    ArrayList<Contract> loadAll();
    
    //杨青
    //根据companyId拿到合同
    public ArrayList<Contract> getContractByCompanyId(Integer companyId);

}
