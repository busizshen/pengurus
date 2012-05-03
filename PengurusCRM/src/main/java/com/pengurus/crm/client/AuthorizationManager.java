package com.pengurus.crm.client;

import java.util.HashSet;
import java.util.Set;

import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class AuthorizationManager {

	private static UserDTO currentUser;

	public static UserDTO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDTO currentUser) {
		AuthorizationManager.currentUser = currentUser;
	}

	public static boolean hasClientAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_CLIENT);
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		return currentUser.haveAuthority(roles);
	}
	
	public static boolean hasClientAccess(QuoteDTO quoteDTO) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		if(quoteDTO.getClient() != null)
			return currentUser.haveAuthority(roles) || currentUser.getId() == quoteDTO.getClient().getId();
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasExecutiveAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasPublicManagerAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasExpertAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_EXPERT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasFreelancerAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasVerificatorAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_VERIFICATOR);
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasAccountantAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewQuotes() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_CLIENT);
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewAll() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_CLIENT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canChangeQuoteStatus() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_CLIENT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canReOpenQuote() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_CLIENT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewProjects() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_CLIENT);
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewAllProjects() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewTasks() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		roles.add(UserRoleDTO.ROLE_EXPERT);
		roles.add(UserRoleDTO.ROLE_VERIFICATOR);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewAllTasks() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewClients() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewExecutives() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_CLIENT);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		roles.add(UserRoleDTO.ROLE_EXPERT);
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		roles.add(UserRoleDTO.ROLE_VERIFICATOR);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewTranslators() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canChangeProject() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewWholeProject() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canChangeTask() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canEditJobProject() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles);
	}
	
	public static boolean canEditJob() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canEditProject(ProjectDTO projectDTO) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		Set<UserRoleDTO> roles2 = new HashSet<UserRoleDTO>();
		roles2.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		return currentUser.haveAuthority(roles) || (currentUser.haveAuthority(roles2) && projectDTO.isProjectManager(currentUser)) ;
	}

	public static boolean hasTranslatorAccess(TaskDTO taskDTO) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXPERT);
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		return currentUser.haveAuthority(roles) && (taskDTO.getExpert() == currentUser);
	}

	public static boolean hasVerificatorAccess(TaskDTO taskDTO) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		return (taskDTO.getReviewer().getId() == currentUser.getId()) || currentUser.haveAuthority(roles);
	}

	public static boolean hasProjectManagerAccess(TaskDTO taskDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isClient() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_CLIENT);
		return currentUser.haveAuthority(roles);
	}

	
	
	
	


}
