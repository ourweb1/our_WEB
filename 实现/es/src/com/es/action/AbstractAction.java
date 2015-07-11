package com.es.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Action的抽象基类
 * @author slave_1
 */
@SuppressWarnings("serial")
public abstract class AbstractAction extends ActionSupport implements ModelDriven, SessionAware {

	protected Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
