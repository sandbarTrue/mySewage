package com.cqupt.project.manager;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.User;

@Repository("userDao")
public interface UserMapper extends BaseDao<User,String>{
int	updateByPrimaryKeySelective(User record);

}