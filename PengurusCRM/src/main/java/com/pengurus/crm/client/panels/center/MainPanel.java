package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.pengurus.crm.client.MainWindow;

public abstract class MainPanel extends LayoutContainer {
	public void setAsMain() {
		MainWindow.addCenterPanel(this);
	}
}
