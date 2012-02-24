package com.pengurus.crm.client.panels.center.user.client;

import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.ClientDTO;

public abstract class ClientPanel extends UserPanel {

	protected ClientDTO clientDTO;
	public ClientPanel(ClientDTO client) {
		super(client);
		this.clientDTO = client;
		userInfoPanel = new UserViewInfo();
	}	

	public ClientPanel() {
		super(null);
		userInfoPanel = new UserViewInfo();
	}

	@Override
	protected String getUserHeading() {
		return "Client";
	}

	@Override
	protected String getUserDescription() {
		if(clientDTO != null)
			return clientDTO.getDescription();
		else 
			return null;
	}

	public abstract ClientDTO getChosenClient();
	

}

