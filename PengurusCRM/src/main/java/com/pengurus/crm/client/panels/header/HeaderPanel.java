package com.pengurus.crm.client.panels.header;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;

public class HeaderPanel extends ContentPanel {
	
	private HeaderPanel() {
		add(new Label("Pengurus CRM"));
		setBottomComponent(new HeaderMenu());
	}
	
	public static ContentPanel getPanel() {
		return new HeaderPanel();
	}
	
}
