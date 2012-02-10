package com.pengurus.crm.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserRole implements IsSerializable {
	
	private Long id;
	private String role;
	
	static public UserRole ROLE_USER = new UserRole("ROLE_USER");
	
	public UserRole(){
		
	}
	public UserRole(String role){
		this.role = role;
	}
		
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return getRole();
	}
	
}

