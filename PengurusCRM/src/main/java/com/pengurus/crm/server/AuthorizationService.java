package com.pengurus.crm.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizationService extends HibernateDaoSupport {

	@Autowired
	private AuthenticationManager am;
	
	private boolean adminMode = false;
	
	public void login(String username, String password){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication auth = am.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public void logout(){
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	}
	
	public void startAdminMode(){
		this.adminMode = true;
	}
	
	public void leaveAdminMode(){
		this.adminMode = false;
	}
	
	public boolean isAdminMode(){
		return this.adminMode;
	}
	
}
