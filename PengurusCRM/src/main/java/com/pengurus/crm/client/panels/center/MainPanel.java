package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.pengurus.crm.client.MainWindow;

public abstract class MainPanel extends ContentPanel {
	
	public MainPanel(){
		this.setLayout(new CenterLayout());
		this.setFrame(true);
	}
	
	public MainPanel(int height){
		this();
		this.setHeight(height);
	}
	
	public void setAsMain() {
		MainWindow.addCenterPanel(this);
	}
}
