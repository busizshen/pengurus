package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public interface TranslatorServiceAsync {

	public void getTranslatorsByRoles(Set<UserRoleDTO> roles, AsyncCallback<Set<TranslatorDTO>> callback);
}
