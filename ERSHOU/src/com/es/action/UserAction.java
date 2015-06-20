package com.es.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.es.service.UserService;
import com.es.vo.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Namespace("/userhome")
public class UserAction extends ActionSupport implements ModelDriven {

	UserInfo user;
	private UserService userService = new UserService();
	
	@Override
	public Object getModel() {
		if(user == null) {
			user = new UserInfo();
		}
		return user;
	}
	
	@Action(value="/login" , results={
			@Result(type="forward" ,location="goodhome/list/listAll?categoryId=1"),
			@Result(type="redirect" , name = "FAILURE" ,location="login.jsp")
	})
	public String check() {
		boolean isOk = userService.auth(user);
		if(!isOk) return "FAILURE0"; 
		return this.SUCCESS;
	}
	
	
	@Action(value="register" , results={
			@Result(type="forward" ,location="goodhome/list/listAll?categoryId=1")
	})
	public String create() {
		userService.create(user);
		return this.SUCCESS;
	}

}
