package com.pengurus.crm.client.panels.center.user.client;

import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientPanel extends UserPanel {

	protected ClientDTO clientDTO;
	public ClientPanel(ClientDTO client) {
		this.clientDTO = client;
	}

	public UserViewInfo getInfoPanel() {
		return new UserViewInfo();
	}
	

	@Override
	protected String getUserHeading() {
		return "Client";
	}

	@Override
	protected String getUsername() {
		return clientDTO.getUsername();
	}

	@Override
	protected String getUserDescription() {
		return clientDTO.getDescription();
	}

	@Override
	protected void addEditionPanel(UserViewInfo userViewInfo) {
		
	}
	

}

