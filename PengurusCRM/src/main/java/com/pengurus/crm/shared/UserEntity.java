package com.pengurus.crm.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserEntity implements IsSerializable {
	private String username;
	
	public UserEntity(){}
	
	public UserEntity(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
