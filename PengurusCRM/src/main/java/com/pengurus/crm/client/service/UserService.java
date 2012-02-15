package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.UserDTO;

@RemoteServiceRelativePath("user.rpc")
public interface UserService extends RemoteService {
	public UserDTO getUser(String username);
	public Void updateUser(UserDTO user);
	public Void updateUserWithPassword(UserDTO user);
	public Void createUser(UserDTO user);
}
