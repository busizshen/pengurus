package com.pengurus.crm.client.panels.header;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class HeaderPanel extends ContentPanel {
	
	public HeaderPanel() {
		setBodyStyleName("header");
		setHeaderVisible(false);
		setBottomComponent(new HeaderMenu());
	}
	
}
