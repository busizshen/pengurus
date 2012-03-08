package com.pengurus.crm.client.panels.center.user.client;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.Widget;
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
	
	@Override
	public Widget getInfoForm() {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		
		TextField<String> login = new TextField<String>();
		login.setFieldLabel("Login");
		login.setReadOnly(true);
		login.setValue(clientDTO.getUsername());
		simple.add(login);
		
		TextField<String> name = new TextField<String>();
		name.setFieldLabel("Name");
		name.setReadOnly(true);
		name.setValue(clientDTO.getFullName());
		simple.add(name);
		
		return simple;
	}


}

