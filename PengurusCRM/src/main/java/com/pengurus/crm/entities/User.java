package com.pengurus.crm.entities;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pengurus.crm.enums.UserRole;

public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Set<UserRole> permission;
    private String username;
    private String password;
    private String description;
     
    public User() {
        super();
    }
    
    public User(Set<UserRole> permission, String username, String password,
                String description) {
        super();
        this.permission = permission;
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

    public Set<UserRole> getPermission() {
        return permission;
    }
    
    public void setPermission(Set<UserRole> permission) {
        this.permission = permission;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
    
}
