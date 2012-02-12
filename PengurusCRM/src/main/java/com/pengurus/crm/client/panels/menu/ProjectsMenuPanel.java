package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class ProjectsMenuPanel extends TabMenuPanel {

	public ContentPanel getPanel() {
		panel = new ContentPanel();
		panel.setHeading("Projects");
		return panel;
	}

}
