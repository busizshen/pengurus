package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;

public class StringModel extends BaseModel {
	private static final long serialVersionUID = -5067358021137367308L;

	public StringModel(String value) {
		super();
		setValue(value);
	}
	
	public void setValue(String value){
		set("value", value);
	}
	
	public String getValue() {
		return get("value");
	}
}
