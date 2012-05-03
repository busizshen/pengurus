package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;

public class FileModel extends BaseModel{

	private static final long serialVersionUID = 6232266037806196346L;

	public FileModel(String  fileName) {
		set("name", fileName);
	}
	
	public String getName(){
		return get("name");
	}
}
