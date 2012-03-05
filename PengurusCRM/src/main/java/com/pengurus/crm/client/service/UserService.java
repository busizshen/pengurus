package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@RemoteServiceRelativePath("user.rpc")
public interface UserService extends RemoteService {
	
	public UserDTO getUser(String username);
	public Void updateUser(UserDTO user);
	public Void updateUserWithPassword(UserDTO user);
	public Boolean checkPassword(String currentPassword, UserDTO user);
	
	public Void createUser(UserDTO user) throws ServiceException;
	
	public Set<UserDTO> getAllUsers();
	public Set<UserDTO> getUsersByRole(Set<UserRoleDTO> roles);
}
