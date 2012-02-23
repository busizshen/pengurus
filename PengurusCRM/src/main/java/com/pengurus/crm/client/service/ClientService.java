package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.ClientDTO;

@RemoteServiceRelativePath("client.rpc")
public interface ClientService extends RemoteService {
	public Set<ClientDTO> getClients();
}
