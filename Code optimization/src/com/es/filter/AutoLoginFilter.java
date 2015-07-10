package com.es.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.es.service.UserService;
import com.es.util.SessionMap;
import com.es.vo.UserInfo;

/**
 * 自动登录拦截器
 * 
 * @author slave_1
 */
public class AutoLoginFilter implements Filter {
	
	private UserService userService = new UserService();
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = ((HttpServletResponse) res);
		HttpSession session = request.getSession(true);
		String username = null, password = null;
		Cookie[] cookies;
		
		username = request.getParameter("name");
		password = request.getParameter("password");
		UserInfo user = (UserInfo) session.getAttribute(SessionMap.USER);
		if (user == null && username == null && password == null) {
			cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for(Cookie c : cookies) {
					if(c.getName().equals("username")) {
						username = c.getValue();
					}
					if(c.getName().equals("password")) {
						password = c.getValue();
					}
				}
				if (username != null && password != null) {
					user = new UserInfo();
					user.setName(username);
					user.setPassword(password);
					
					boolean isOk = userService.auth(user);
					if(isOk) {
						session.setAttribute(SessionMap.USER, user); // user添加到session中。
					}
				} 
			} 
		}
		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
