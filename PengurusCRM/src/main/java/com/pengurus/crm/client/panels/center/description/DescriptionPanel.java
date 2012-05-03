package com.pengurus.crm.client.panels.center.description;

import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextArea;

public abstract class DescriptionPanel extends FieldSet {

	protected TextArea description;

	public DescriptionPanel(String descr, Integer height ,Integer width) {
		setHeading("Description");
		description = new TextArea();
		description.setHeight(height);
		description.setWidth(width);
		description.setValue(descr);
		description.setStyleName("boxsizingBorder");
		add(description);
		this.setAutoHeight(true);
	}

	public DescriptionPanel( Integer height ,Integer width) {
		setHeading("Description");
		description = new TextArea();
		description.setHeight(height);
		description.setWidth(width);
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