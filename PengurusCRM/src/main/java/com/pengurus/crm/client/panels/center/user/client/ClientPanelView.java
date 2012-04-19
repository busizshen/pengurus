package com.pengurus.crm.client.panels.center.user.client;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientPanelView extends ClientPanel {

	public ClientPanelView(ClientDTO client,String heading) {
		super(client,heading);
	}

	@Override
	public ClientDTO getChosenClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addEditionPanel(HorizontalPanel hp) {
		// TODO Auto-generated method stub
		
	}

}
