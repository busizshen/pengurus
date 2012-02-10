package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.User;

public interface CurrentSessionServiceAsync {
	public void getCurrentUser(AsyncCallback<User> callback);
}
