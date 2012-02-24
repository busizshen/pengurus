package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;

public class UserModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8134206653269091514L;

	public String getId(){
		return get("id");
	}
	
	public String getUsername(){
		return get("username");
	}
	
	public String getDescription(){
		return get("descritpion");
	}
}
