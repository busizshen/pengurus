package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public interface UserServiceAsync {
	public void getUser(String username, AsyncCallback<UserDTO> callback);
	public void updateUser(UserDTO user, AsyncCallback<Void> callback);
	public void updateUserWithPassword(UserDTO user, AsyncCallback<Void> callback);
	public void checkPassword(String currentPassword, UserDTO user,AsyncCallback<Boolean> callback);

	public void createUser(UserDTO user, AsyncCallback<Void> callback);
	
	public void getAllUsers(AsyncCallback<Set<UserDTO>> callback);
	public void getUsersByRole(Set<UserRoleDTO> roles,AsyncCallback<Set<UserDTO>> callback);
}
