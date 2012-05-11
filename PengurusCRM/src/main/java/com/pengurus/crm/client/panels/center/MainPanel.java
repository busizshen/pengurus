package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.i18nMessages;

public abstract class MainPanel extends ContentPanel {
	protected i18nConstants myConstants;
	protected i18nMessages myMessages;
	public MainPanel(){
		this.setLayout(new CenterLayout());
		this.setFrame(true);
		myConstants = (i18nConstants)GWT.create(i18nConstants.class);
		myMessages=(i18nMessages)GWT.create(i18nMessages.class);

	}
	
	public MainPanel(int height){
		this();
		this.setHeight(height);
		myConstants = (i18nConstants)GWT.create(i18nConstants.class);

	}
	
	public void setAsMain() {
		MainWindow.addCenterPanel(this);
	}
}
