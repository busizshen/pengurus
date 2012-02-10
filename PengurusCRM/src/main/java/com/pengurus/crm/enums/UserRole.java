package com.pengurus.crm.enums;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {
	
	private static final long serialVersionUID = 2643977776593782708L;
	 
	private Long id;
	private String role;
	
	static public UserRole USER_ROLE = new UserRole("USER_ROLE");
	
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
	public String getAuthority() {
		return role;
	}
	
	@Override
	public String toString() {
		return getAuthority();
	}
	
}

