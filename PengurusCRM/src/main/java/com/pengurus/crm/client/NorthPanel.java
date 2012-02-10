package com.pengurus.crm.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;

public class NorthPanel {

	public ContentPanel getPanel() {
		// TODO Auto-generated method stub
		ContentPanel north_panel = new ContentPanel();
		north_panel.add(new Label("Pengurus CRM"));
		return north_panel;
	}

}
