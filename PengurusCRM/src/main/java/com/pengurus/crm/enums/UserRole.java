package com.pengurus.crm.enums;

import org.springframework.security.core.GrantedAuthority;

public abstract class UserRole implements GrantedAuthority {
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

