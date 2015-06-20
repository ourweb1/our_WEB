package com.es.service;

import com.es.dao.UserDAO;
import com.es.model.User;
import com.es.vo.UserInfo;

public class UserService {
	
	private UserDAO userDao = new UserDAO();

	public boolean auth(UserInfo user) {
		User u = userDao.match(user.getName());
		if(u == null || 
				!u.getPassword().equals(user.getPassword()))
			return false;
		return true;
	}

	public void create(UserInfo user) {
		userDao.add(user);
	}

}
