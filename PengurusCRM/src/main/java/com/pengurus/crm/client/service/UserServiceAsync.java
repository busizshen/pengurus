package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

public interface UserServiceAsync {
	public void getUser(String username, AsyncCallback<UserDTO> callback);
	public void updateUser(UserDTO user, AsyncCallback<Void> callback);
	public void updateUserWithPassword(UserDTO user, AsyncCallback<Void> callback);
	public void checkPassword(String currentPassword, UserDTO user,AsyncCallback<Boolean> callback);

	public void createUser(UserDTO user, AsyncCallback<Void> callback);
	
	public void getAllUsers(AsyncCallback<Set<UserDTO>> callback);
	public void getUsersByRoles(Set<UserRoleDTO> roles, AsyncCallback<Set<UserDTO>> callback);

	public void getPaginatedAllUsers(PagingLoadConfigHelper loadConfig,
			AsyncCallback<PagingLoadResultHelper<UserModel>> callback);
	public void getPaginatedUsersByRoles(PagingLoadConfigHelper loadConfig,
			Set<UserRoleDTO> roles,
			AsyncCallback<PagingLoadResultHelper<UserModel>> callback);
}
