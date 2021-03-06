package com.pengurus.crm.shared.dto;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserDTO implements IsSerializable {

    private Long id;
    private Set<UserRoleDTO> authorities = new HashSet<UserRoleDTO>();
    private String username;
    private String password;
    private String description;
    
    public UserDTO(){
        
    }
    
    public UserDTO(Long id){
        this.id = id;
    }
    
    public UserDTO(Long id, Set<UserRoleDTO> authorities, String username,
            String password, String description) {
        this.id = id;
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
    
    public Set<UserRoleDTO> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(Set<UserRoleDTO> authorities) {
        this.authorities = authorities;
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

	public boolean haveAuthority(Set<UserRoleDTO> roles) {
		if(roles.size() == 0)
			return true;
		for(UserRoleDTO role : roles){
			if( authorities.contains(role))
				return true;
		}
		return false;
	}
    
	public String getFullName () {
		return getUsername();
	}
	
}
