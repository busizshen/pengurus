package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

@RemoteServiceRelativePath("user.rpc")
public interface UserService extends RemoteService {
	
	public UserDTO getUser(String username);
	public Void updateUser(UserDTO user) throws ServiceException;
	public Void updateUserWithPassword(UserDTO user) throws ServiceException;
	public Boolean checkPassword(String currentPassword, UserDTO user);
	
	public Void createUser(UserDTO user) throws ServiceException, UsernameExistsException;
	
	public Set<UserDTO> getAllUsers();
	public Set<UserDTO> getUsersByRoles(Set<UserRoleDTO> roles);
	
	public PagingLoadResultHelper<UserModel> getPaginatedAllUsers(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<UserModel> getPaginatedUsersByRoles(PagingLoadConfigHelper loadConfig, Set<UserRoleDTO> roles);
}
