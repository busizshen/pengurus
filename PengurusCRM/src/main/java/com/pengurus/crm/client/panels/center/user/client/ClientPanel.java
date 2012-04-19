package com.pengurus.crm.client.panels.center.user.client;

import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.ClientDTO;

public abstract class ClientPanel extends UserPanel {
	public ClientPanel(ClientDTO client,String heading) {
		super(client,heading);
		initPanel();
		refresh(client);
	}	

	public ClientPanel() {
		super(null,"");
		initPanel();
	}

	public abstract ClientDTO getChosenClient();

	public void refresh(ClientDTO client) {
		userDTO = client;
		if(client != null){
			name.setText(client.getFullName());
		}
	}
}

