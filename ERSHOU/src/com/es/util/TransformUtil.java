package com.es.util;

import com.es.model.Good;
import com.es.model.User;
import com.es.vo.GoodInfo;
import com.es.vo.UserInfo;

public class TransformUtil {
	
	public static GoodInfo goodTransform(Good good) {
		GoodInfo info = new GoodInfo();
		info.setDetail(good.getDetail());
		info.setId(good.getId());
		info.setImgSrc(good.getImgSrc());
		info.setName(good.getName());
		info.setPrice(good.getPrice());
		UserInfo ui = userTransform(good.getUser());
		info.setUserInfo(ui);
		return info;
	}
	
	public static UserInfo userTransform(User user) {
		UserInfo ui = new UserInfo();
		ui.setEmail(user.getEmail());
		ui.setId(user.getId());
		ui.setName(user.getName());
		ui.setTelephone(user.getTelephone());
		return ui;
	}
}
