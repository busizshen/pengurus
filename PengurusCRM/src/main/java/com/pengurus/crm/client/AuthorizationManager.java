package com.pengurus.crm.client;

import java.util.HashSet;
import java.util.Set;

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

	public static boolean hasExecutiveAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		return currentUser.haveAuthority(roles);
	}

	public static boolean hasPublicManagerAccess() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
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
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
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
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		roles.add(UserRoleDTO.ROLE_EXPERT);
		roles.add(UserRoleDTO.ROLE_VERIFICATOR);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewAllTasks() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewClients() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewExecutives() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_CLIENT);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		roles.add(UserRoleDTO.ROLE_EXPERT);
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		roles.add(UserRoleDTO.ROLE_VERIFICATOR);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewTranslators() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canChangeProject() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		return currentUser.haveAuthority(roles);
	}

	public static boolean canViewWholeProject() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		roles.add(UserRoleDTO.ROLE_ACCOUNTANT);
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		return currentUser.haveAuthority(roles);
	}

}
