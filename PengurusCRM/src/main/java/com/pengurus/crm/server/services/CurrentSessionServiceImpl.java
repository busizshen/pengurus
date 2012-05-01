package com.pengurus.crm.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.exceptions.IncorrectPasswordException;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.UserDTO;

public class CurrentSessionServiceImpl implements CurrentSessionService {

	private final Logger log = LoggerFactory.getLogger("CurrentSessionServiceImpl");
	
	private UserDAO userDAO;
	private UserService userService;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDTO getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			User user = userDAO.getUserByUsername(((UserDetails) principal).getUsername());
			log.error(user.toString());
			return user.toDTO();
		}
		return null;
	}

	@Override
	public Void setPassword(String currentPassword, String password) throws IncorrectPasswordException, ServiceException {
		UserDTO user = getCurrentUser();
		if (!userService.checkPassword(currentPassword, user)) {
			throw new IncorrectPasswordException();
		}
		user.setPassword(password);
		userService.updateUserWithPassword(user);
		return null;
	}


}