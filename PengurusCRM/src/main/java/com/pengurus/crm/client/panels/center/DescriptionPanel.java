package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.Element;

public class DescriptionPanel extends ContentPanel {
 
	protected TextArea a;
	
	public DescriptionPanel(String description) {
		 setHeading("Description");
		 a =new  TextArea();
		 a.setHeight(100);
		 a.setWidth(300);
		 a.setValue(description);
		 this.setAutoWidth(true);
		 this.setCollapsible(true);
		 this.setAnimCollapse(true);
		 this.setAutoHeight(true);
		 add(a);
	 }

			@Override  
			protected void onRender(Element parent, int index) {  
			    super.onRender(parent, index); }

			public void setNonEditable() {
			// a.setEnabled(disabled);
			}
	}  