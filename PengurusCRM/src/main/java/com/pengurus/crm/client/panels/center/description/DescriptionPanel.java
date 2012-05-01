package com.pengurus.crm.client.panels.center.description;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;

public class DescriptionPanel extends ContentPanel {

	protected TextArea description;

	public DescriptionPanel(String descr) {
		setHeading("Description");
		description = new TextArea();
		description.setHeight(100);
	//	description.setWidth(300);
		description.setValue(descr);
		setWidth(300);
		description.setStyleName("boxsizingBorder");

		add(description);
		this.setAutoHeight(true);
	}

	public DescriptionPanel() {
		setHeading("Description");
		description = new TextArea();
/*		description.setHeight(100);
		description.setWidth(300);*/
		setWidth(300);
		description.setStyleName("boxsizingBorder");
		add(description);
		this.setAutoHeight(true);
	}


	public String getDescription() {
		return description.getValue();
	}
	
	public void setDescription(String descr) {
		description.setValue(descr);
	}
	
	public void setAllowBlank(boolean allow){
		description.setAllowBlank(allow);
	}

	
}