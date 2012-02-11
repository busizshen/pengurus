package com.pengurus.crm.server;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.entities.UserRole;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

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
	public UserDTO getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.error("===> Wchodze");
		
		if (principal instanceof UserDetails) {
			User user = userDAO.findByUsername(((UserDetails) principal).getUsername());
		//	User user = new User(new HashSet<UserRole>(), "a", "b", "c");
			log.error(user.toString());
			return createUserDTO(user);
		}
		
		log.error("===> Null");
		
		return null;
	}
	
	private UserDTO createUserDTO(User user){
	    HashSet<UserRoleDTO> authoritiesDTOs = new HashSet<UserRoleDTO>();
	    for(UserRole userRole : user.getAuthorities())
	        authoritiesDTOs.add(createUserRoleDTO(userRole));
	    return new UserDTO(user.getId(), authoritiesDTOs,
	                       user.getUsername(), user.getPassword(),
	                       user.getDescription());
	}
	
	private UserRoleDTO createUserRoleDTO(UserRole userRole){
	    return new UserRoleDTO(userRole.getId(), userRole.getRole());
	}

}