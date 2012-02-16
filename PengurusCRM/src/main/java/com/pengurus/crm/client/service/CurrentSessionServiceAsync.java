package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.UserDTO;

public interface CurrentSessionServiceAsync {
	public void getCurrentUser(AsyncCallback<UserDTO> callback);
	public void setPassword(String currentPassword, String password, AsyncCallback<Void> callback);
}
