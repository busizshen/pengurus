package com.pengurus.crm.entities;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pengurus.crm.enums.UserRole;

public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Set<UserRole> authorities;
    private String username;
    private String password;
    private String description;
     
    public User() {
        super();
    }
    
    public User(Set<UserRole> permission, String username, String password,
                String description) {
        super();
        this.authorities = permission;
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
        return authorities;
    }
    
    public void setPermission(Set<UserRole> permission) {
        this.authorities = permission;
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
		return authorities;
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
