package com.cqupt.project.controller;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cqupt.project.model.Company;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.model.User;
import com.cqupt.project.service.CompanyService;
import com.cqupt.project.service.UserService;
import com.cqupt.project.utils.JSONUtils;
@Controller("userController")
@RequestMapping("/user")
public class UserController {

		@Resource
		private UserService userService ;
		@Resource
		private CompanyService companyService;
	
		@RequestMapping("/login.do")
		public void Login(User u , HttpServletResponse response,HttpServletRequest request){
			Map<String,Object> map  = new HashMap<String,Object>();
		      boolean result=userService.isUserRegister(u.getUsername());
		      boolean result2=userService.isLogin(u);
		        if(result && result2){
		        request.getSession().setAttribute("username", u.getUsername());
				map.put("status", ReturnStatus.SUCCSS);
				map.put("msg", "登录成功");
				map.put("data",u.getUsername());
		        }
		       else if(!result){
		    	map.put("status",ReturnStatus.ERROR);
		    	map.put("msg", "用户名不存在");
		    	map.put("data",null);
		    	
		       }
		       else{
		    	map.put("status",ReturnStatus.ERROR);
			    map.put("msg", "密码错误");
			    map.put("data",null);  
		    	   
		       }
		
			JSONUtils.toJSON(map, response);
		}
		  @RequestMapping("/register.do")
		   public void Register(Company company,User user, HttpServletResponse response){
				Map<String,Object> map  = new HashMap<String,Object>();
			   boolean result=userService.isRegister(user.getUsername(),company.getRegistNum());
			   if(result){
				   map.put("status",ReturnStatus.ERROR);
			       map.put("msg","用户名已存在或公司已经注册");
			       map.put("data", null);
			   }
			   else{
				   /*System.out.println(user.getUsername());*/
				 /*  companyService.addCompany(company);*/
				   companyService.addCompany(company);
				   user.setCompanyId(companyService.getCompanyIdByregistNum(company.getRegistNum()));
				   result= userService.addUser(user);
				  if(result){
					  map.put("status", ReturnStatus.SUCCSS);
					  map.put("msg","成功");
				      map.put("data", null);
				  }
				  else{
					map.put("status",ReturnStatus.ERROR);
					map.put("msg","插入失败");
					map.put("data", null);
				  }
					  
			   }
			  
			   JSONUtils.toJSON(map, response);
		   }
		  @RequestMapping("/isLogin.do")
		  public boolean islogin(HttpServletRequest request){
			if(request.getSession().getAttribute("username")!=null){
				return true;
			}
			  return false;
		  }
}
