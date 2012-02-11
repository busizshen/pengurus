package com.pengurus.crm.client.panel.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class AdministrationMenuPanel extends TabMenuPanel {

	@Override
	public ContentPanel getPanel() {
		panel = new ContentPanel();
		panel.setHeading("Administration");
		return panel;
	}

}
