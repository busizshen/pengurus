package com.pengurus.crm.client.panels.center.user.client;

import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientPanelView extends ClientPanel {

	public ClientPanelView(ClientDTO client) {
		super(client);
		userInfoPanel = new UserViewInfo();
	}

	@Override
	public ClientDTO getChosenClient() {
		// TODO Auto-generated method stub
		return null;
	}

}
