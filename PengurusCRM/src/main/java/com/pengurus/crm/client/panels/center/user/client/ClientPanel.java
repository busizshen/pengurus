package com.pengurus.crm.client.panels.center.user.client;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.ClientDTO;

public abstract class ClientPanel extends UserPanel {
	TextField<String> login;
	TextField<String> name ;
	protected ClientDTO clientDTO;
	public ClientPanel(ClientDTO client,String heading) {
		super(client,heading);
		refresh(client);
	}	

	public ClientPanel() {
		super(null,"");
	}

	public abstract ClientDTO getChosenClient();
	
	@Override
	public Widget getInfoForm() {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		
		login = new TextField<String>();
		login.setFieldLabel("Login");
		login.setReadOnly(true);
		if(clientDTO != null)
			login.setValue(clientDTO.getUsername());
		simple.add(login);
		
		name = new TextField<String>();
		name.setFieldLabel("Name");
		name.setReadOnly(true);
		if(clientDTO != null)
			name.setValue(clientDTO.getFullName());
		simple.add(name);
		
		return simple;
	}

	public void refresh(ClientDTO client) {
		clientDTO = client;
		userDTO = client;
		if(client != null){
			login.setValue(clientDTO.getUsername());
			name.setValue(clientDTO.getFullName());
			descriptionPanel.setDescription(clientDTO.getDescription());
		}
	}
}

