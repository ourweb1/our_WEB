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
		user.setEmail(u.getEmail());
		user.setId(u.getId());
		user.setTelephone(u.getTelephone());
		return true;
	}

	public void create(UserInfo user) {
		userDao.add(user);
	}

}
