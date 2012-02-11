package com.pengurus.crm.entities;

import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable {

	private Long id;
	private Set<UserRole> authorities;
	private String username;
	private String password;
	private String description;

	public User() {
		super();
	}

	public User(Set<UserRole> authorities, String username, String password,
			String description) {
		super();
		this.authorities = authorities;
		this.username = username;
		this.password = password;
		this.description = description;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserRole> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<UserRole> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User: ");
		sb.append(username);
		sb.append("; Desription: ");
		sb.append(description);
		sb.append("; Authorities:");
		sb.append(authorities);
		return sb.toString();
	}
}
