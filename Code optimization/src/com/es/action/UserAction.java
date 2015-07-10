package com.es.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.es.service.UserService;
import com.es.util.SessionMap;
import com.es.vo.UserInfo;


@Namespace("/user")
public class UserAction extends AbstractAction implements ServletRequestAware , ServletResponseAware{

	private static final long serialVersionUID = 1L;
	
	private UserInfo user;
	
	private String redirectUrl;

	private UserService userService = new UserService();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Override
	public UserInfo getModel() {
		if(user == null) {
			user = new UserInfo();
		}
		return user;
	}
	
	
	@Action(value="login" , results={
			@Result(location="${redirectUrl}" , type="redirect"),
			@Result(name = "FAILURE" ,location="/user/login.jsp")
	})
	public String check() {
System.out.println("--------second?----");
		if(redirectUrl == null) {
			redirectUrl = "/good/listAll?id=1";
		}
		if(session.get(SessionMap.USER) != null) return this.SUCCESS;
		
		if(user.getName() == null || user.getPassword() == null) {
			return "FAULURE";
		}
		boolean isOk = userService.auth(user);
		
		if(!isOk) return "FAILURE"; 
		
		session.put(SessionMap.USER, user);
		
		
		if(user.getAutoLogin() != null && user.getAutoLogin().equals("true") ) {
			Cookie cookie1 = new Cookie("username" , user.getName());
			Cookie cookie2 = new Cookie("password" , user.getPassword());
			cookie1.setPath("/");
			cookie1.setMaxAge(10000);
			cookie2.setMaxAge(10000);
			cookie2.setPath("/");
			response.addCookie(cookie1);
			response.addCookie(cookie2);
		}
		return this.SUCCESS;
	}
	
	
	@Action(value="register" , results={
			@Result(location="/good/listAll?id=1" , type="redirect")
	})
	public String create() {
		userService.create(user);
		return this.SUCCESS;
	}
	
	@Action(value="logout" , results={
			@Result(location="/user/login.jsp" , type="redirect")
	})
	public String logout() {
		session.remove(SessionMap.USER);
		return this.SUCCESS;
	}
	
	
	
	public UserInfo getUser() {
		return user;
	}


	public void setUser(UserInfo user) {
		this.user = user;
	}


	public String getRedirectUrl() {
		return redirectUrl;
	}


	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
