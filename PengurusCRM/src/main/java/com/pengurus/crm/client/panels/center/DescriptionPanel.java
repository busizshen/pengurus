package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;

public class DescriptionPanel extends ContentPanel {

	protected TextArea a;

	public DescriptionPanel(String description) {
		setHeading("Description");
		a = new TextArea();
		a.setHeight(100);
		a.setWidth(300);
		a.setValue(description);
		this.setCollapsible(true);
		this.setAnimCollapse(true);
		this.collapse();
		add(a);
		this.setAutoWidth(true);
		this.setAutoHeight(true);
	}

	public DescriptionPanel() {
		setHeading("Description");
		a = new TextArea();
		a.setHeight(100);
		a.setWidth(300);
		this.setCollapsible(true);
		this.setAnimCollapse(true);
		this.collapse();
		add(a);
		this.setAutoWidth(true);
		this.setAutoHeight(true);
	}

	public void setNonEditable() {
		// a.setEnabled(disabled);
	}

	public String getDescription() {
		return a.getValue();
	}

	
}