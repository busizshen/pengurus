package com.pengurus.crm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class User {

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
		this();
		this.authorities = authorities;
		this.username = username;
		this.password = password;
		this.description = description;
	}
	
	public User(UserDTO userDTO) {
		this.authorities = new HashSet<UserRole>();
		for (UserRoleDTO userRole: userDTO.getAuthorities()) {
			authorities.add(new UserRole(userRole.getId(), userRole.getRole()));
		}
		this.username = userDTO.getUsername();
		this.password = userDTO.getPassword();
		this.description = userDTO.getDescription();
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

	public boolean isPasswordCorrect(String password) {
		return false;
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
	
	public UserDetails toUserDetails() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (UserRole userRole : getAuthorities()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole
					.toString()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				getUsername(), getPassword(), grantedAuthorities);
		return userDetails;
	}

	public UserDTO toUserDTO() {
	    HashSet<UserRoleDTO> authoritiesDTOs = new HashSet<UserRoleDTO>();
	    for(UserRole userRole : getAuthorities())
			authoritiesDTOs.add(userRole.toUserRoleDTO());
	    return new UserDTO(getId(), authoritiesDTOs,
	                       getUsername(), getPassword(),
	                       getDescription());
	}
}
