package com.pengurus.crm.client.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectsMenuPanel extends TabMenuPanel {

	public ContentPanel getPanel() {
		panel = new ContentPanel();
		panel.setHeading("Projects");
		return panel;
	}

}
