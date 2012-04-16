package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum UserRoleDTO implements IsSerializable {
    
	ROLE_ACCOUNTANT ("accountant"),
	ROLE_EXECUTIVE ("executive"),
	ROLE_EXPERT ("expert"),
	ROLE_FREELANCER ("freelancer"),
	ROLE_USER ("user"),
	ROLE_CLIENT ("client"),
	ROLE_VERIFICATOR ("verificator"),
	ROLE_PROJECTMANAGER ("project manager");
	
	private final String name;
	
	UserRoleDTO (String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
