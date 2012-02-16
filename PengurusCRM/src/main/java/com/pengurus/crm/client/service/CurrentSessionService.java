package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.service.exceptions.IncorrectPasswordException;
import com.pengurus.crm.shared.dto.UserDTO;

@RemoteServiceRelativePath("currentSession.rpc")
public interface CurrentSessionService extends RemoteService {
	public UserDTO getCurrentUser();
	public Void setPassword(String currentService, String password) throws IncorrectPasswordException;
}
