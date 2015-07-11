package com.es.vo;

import java.util.List;

/**
 * @author slave_1
 * 客户端 与 服务端参数封装对象
 */
public class GoodInfo {
	private int id;
	private String imgSrc;
	private String name;
	private double price;
	private String detail;
	
	private UserInfo user;
//	private List<CategoryInfo> categories = new ArrayList<CategoryInfo> ();
	private List<CategoryInfo> categories;
	

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<CategoryInfo> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryInfo> categories) {
		this.categories = categories;
	}
}
