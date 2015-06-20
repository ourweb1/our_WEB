/**
 * 
 */
package com.es.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.es.service.GoodService;
import com.es.vo.GoodInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author slave_1
 * 二手商品相关action
 */
@Namespace("/goodhome")
public class GoodAction extends ActionSupport implements ModelDriven {
	
	GoodInfo good ;
	List<GoodInfo> goods;
	
	private GoodService goodService = new GoodService();

	@Override
	public Object getModel() {
		if(good == null) {
			good = new GoodInfo();
		}
		return null;
	}
	
	@Action(value="/list" , results={
			@Result(location="content.jsp")
	})
	public String listAll() {
		goods = goodService.findAll(good);
		return this.SUCCESS;
	}
	
	@Action(value="/detail" , results= {
			@Result(location="detail.jsp")
	} )
	public String view() {
		good = goodService.find(good);
		return this.SUCCESS;
	}
	
	@Action(value = "/publish"  , results={
			@Result(location="success.jsp")
	})
	public String newGood() {
		goodService.create(good);
		return this.SUCCESS;
	}

}
