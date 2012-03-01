package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.pengurus.crm.client.MainWindow;

public abstract class MainPanel extends ContentPanel {
	public void setAsMain() {
		MainWindow.addCenterPanel(this);
	}
}
