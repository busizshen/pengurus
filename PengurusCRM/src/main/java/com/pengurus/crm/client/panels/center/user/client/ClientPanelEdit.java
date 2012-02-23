package com.pengurus.crm.client.panels.center.user.client;

import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientPanelEdit extends ClientPanel{

	public ClientPanelEdit(ClientDTO client) {
		super(client);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void addEditionPanel(UserViewInfo userViewInfo) {
		ClientsListPanel cl = new ClientsListPanel();
		userViewInfo.add(cl.getPanel());
	}

}
