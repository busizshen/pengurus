package com.pengurus.crm.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class PermissionEvaluatorImpl implements PermissionEvaluator {

	final Logger logger = LoggerFactory
			.getLogger(PermissionEvaluatorImpl.class);

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {

		if (targetDomainObject == null) {
			logger.error("null conditions must be handled directly in the security expression");
			return false;
		} else if (authentication.getName() == null) {
			logger.error("anonymous conditions must be handled directly in the security expression");
			return false;
		} else if (!(permission instanceof String)) {
			logger.error("unknown permission type: {}", permission);
			return false;
		}
		
		if (targetDomainObject instanceof UserDTO) {
			return hasPermission(authentication, (UserDTO)targetDomainObject, permission);
		} else if (targetDomainObject instanceof ProjectDTO) {
			return hasPermission(authentication.getName(), (ProjectDTO)targetDomainObject, permission);
		}	else if (targetDomainObject instanceof QuoteDTO) {
			return hasPermission(authentication.getName(), (QuoteDTO)targetDomainObject, permission);
		}
		return false;
	}

	private boolean hasPermission(String name, QuoteDTO quote,
			Object permission) {
		logger.error(quote.getClient().getUsername());

		logger.error(name);
		if(quote.getClient() != null)
			return quote.getClient().getUsername().equals(name);
		return false;
	}

	private boolean hasPermission(Authentication authentication,
			UserDTO user, Object permission) {
		String username = authentication.getName();
		if ("read".equals(permission)) {
			return username.equals(user.getUsername()) ||
					user.getAuthorities().contains(UserRoleDTO.ROLE_EXECUTIVE) ||
					authentication.getAuthorities().contains(UserRoleDTO.ROLE_WORKER.toString());
		} else if ("write".equals(permission)) {
			return username.equals(user.getUsername());
		}
		return false;
	}

	private boolean containUsername(List<UserDTO> users, String username) {
		for (UserDTO user: users) {
			if (username.equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasPermission(String username, ProjectDTO project, Object permission) {
		if ("read".equals(permission)) {
			return containUsername(new ArrayList<UserDTO>(project.getExperts()), username) ||
					containUsername(new ArrayList<UserDTO>(project.getFreelancers()), username) ||
					containUsername(new ArrayList<UserDTO>(project.getProjectManagers()), username);	
		} else if ("write".equals(permission)) {
			return containUsername(new ArrayList<UserDTO>(project.getProjectManagers()), username);
		}
		return false;
	}
	
	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
