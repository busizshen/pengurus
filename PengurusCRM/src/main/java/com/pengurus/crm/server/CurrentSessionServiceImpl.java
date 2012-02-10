package com.pengurus.crm.server;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.shared.User;
import com.pengurus.crm.shared.UserRole;

public class CurrentSessionServiceImpl implements CurrentSessionService {

	private final Logger log = LoggerFactory.getLogger("CurrentSessionServiceImpl");
	
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.error("===> Wchodze");
		
		if (principal instanceof UserDetails) {
		//	User user = userDAO.findByUsername(((UserDetails) principal).getUsername());
			User user = new User(new HashSet<UserRole>(), "a", "b", "c");
			log.error(user.toString());
			return user;
		}
		
		log.error("===> Null");
		
		return null;
	}

}
