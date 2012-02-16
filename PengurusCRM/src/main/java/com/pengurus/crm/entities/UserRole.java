package com.pengurus.crm.entities;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class UserRole implements IsSerializable {
	
	private Long id;
	private String role;
	
	static public UserRole ROLE_USER = new UserRole("ROLE_USER");
	
	public UserRole(){
		
	}
	public UserRole(String role){
		this.role = role;
	}
	
	public UserRole(Long id, String role) {
		super();
		this.id = id;
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
	
	public UserRoleDTO toUserRoleDTO() {
	    return new UserRoleDTO(getId(), getRole());
		
	}
	
}

