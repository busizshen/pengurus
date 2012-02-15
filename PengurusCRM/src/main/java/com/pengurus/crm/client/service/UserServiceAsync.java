package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.UserDTO;

public interface UserServiceAsync {
	public void updateUser(UserDTO user, AsyncCallback<Void> callback);
	public void updateUserWithPassword(UserDTO user, AsyncCallback<Void> callback);
	public void createUser(UserDTO user, AsyncCallback<Void> callback);
}
