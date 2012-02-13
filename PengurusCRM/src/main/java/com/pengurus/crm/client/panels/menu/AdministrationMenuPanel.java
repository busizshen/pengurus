package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class AdministrationMenuPanel extends TabMenuPanel {

	@Override
	public ContentPanel getPanel() {
		panel = new ContentPanel();
		panel.setHeading("Administration");
		setPanelStyle();
		return panel;
	}

}
