package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.ClientDTO;

public interface ClientServiceAsync {
	public void getClients(AsyncCallback<Set<ClientDTO>> callback);
}
