package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@RemoteServiceRelativePath("translator.rpc")
public interface TranslatorService extends RemoteService{
	public Set<TranslatorDTO> getTranslatorsByRoles(Set<UserRoleDTO> roles);
}
