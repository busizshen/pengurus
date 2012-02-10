package com.pengurus.crm.client.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class TasksMenuPanel extends TabMenuPanel {

	@Override
	public ContentPanel getPanel() {
		// TODO Auto-generated method stub
		panel = new ContentPanel();
		panel.setHeading("Tasks");
		return panel;
	}

}
