package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.ClientService;
import com.pengurus.crm.daos.ClientDAO;
import com.pengurus.crm.entities.Client;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientServiceImpl implements ClientService {
	private ClientDAO clientDAO;

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@Override
	public Set<ClientDTO> getClients() {
		List<Client> list = clientDAO.loadAll();
		Set<ClientDTO> set = new HashSet<ClientDTO>();
		for(Client q : list){
			set.add(q.toDTOLazy());
		}
		return set;
	}
}
