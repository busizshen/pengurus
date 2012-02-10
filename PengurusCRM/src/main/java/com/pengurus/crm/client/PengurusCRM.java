package com.pengurus.crm.client;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class PengurusCRM implements EntryPoint {
	public MainWindow mainWindow;
	public void onModuleLoad() {
		
		mainWindow = GWT.create(MainWindow.class);
		RootPanel.get().add(mainWindow);
	
	}
	public MainWindow getMain_window() {
		return mainWindow;
	}

}
