package com.cqupt.project.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.project.manager.CompanyMapper;
import com.cqupt.project.manager.UserMapper;
import com.cqupt.project.model.Company;
import com.cqupt.project.model.User;
import com.cqupt.project.service.UserService;
import com.cqupt.project.utils.MD5;

@Service("userLoginService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userDao;
	@Resource
	private CompanyMapper companyDao;
	@Override
	public boolean addUser(User user){
		if(user.getPassword()!="" ){
		try{
		user.setPassword(MD5.Encryption(user.getPassword()));
		userDao.insert(user);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		}
		else{
			return false;
		}
	}
	
@Override
  public boolean isRegister(String username,long registNum){
	  
		User user=userDao.selectByPrimaryKey(username);
		Company company=companyDao.selectByregistNum(registNum);
		if(user==null && company==null){
			return false;
		}
		return true;
	
  }	
public boolean isUserRegister(String username){
	User user=userDao.selectByPrimaryKey(username);
	if(user==null ){
		return false;
	}
	return true;

}	
	@Override
  public boolean modifyUser(User user){
	  try{
		  userDao.updateByPrimaryKeySelective(user);
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  return false;
	  }
	  return true;
  }
	@Override
  public User loaduser(String username){
	  User u;
	  try{
		   u=userDao.selectByPrimaryKey(username);
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  return null;
	  }
	  return u;
  }

	@Override
	public boolean isLogin(User u) {
		// TODO Auto-generated method stub
		//==比较的是地址 equals重写后比较的是内容
		User u2=userDao.selectByPrimaryKey(u.getUsername());
		if(u2!=null){
		if(u2.getPassword().equals(MD5.Encryption(u.getPassword()))){
			return true;
		}
		return false;
		}
		return false;
	}
}
