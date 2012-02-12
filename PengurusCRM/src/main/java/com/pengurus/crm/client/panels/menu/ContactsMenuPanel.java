package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class ContactsMenuPanel extends TabMenuPanel {

	@Override
	public ContentPanel getPanel() {
		panel = new ContentPanel();
		panel.setHeading("Contacts");
		return panel;
	}

}
