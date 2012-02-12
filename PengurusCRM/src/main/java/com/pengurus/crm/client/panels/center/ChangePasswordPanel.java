package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;

public class ChangePasswordPanel extends LayoutContainer {
	
	private VerticalPanel verticalPanel;
	private FormData formData; 
	
	private ChangePasswordPanel() {
		FormPanel form = new FormPanel();
		
		form.setHeading("Change Password");
		form.setFrame(true);
		
		TextField<String> oldPassword = new TextField<String>();
		oldPassword.setPassword(true);
		oldPassword.setFieldLabel("Current password");
		oldPassword.setAllowBlank(false);
		form.add(oldPassword, formData);
		
		TextField<String> newPassword = new TextField<String>();
		newPassword.setPassword(true);
		newPassword.setFieldLabel("New password");
		newPassword.setAllowBlank(false);
		form.add(newPassword, formData);
		
		TextField<String> confirmedPassword = new TextField<String>();
		confirmedPassword.setPassword(true);
		confirmedPassword.setFieldLabel("Confirm new password");
		confirmedPassword.setAllowBlank(false);
		form.add(confirmedPassword, formData);
		
	    Button b = new Button("Submit");  
	    form.addButton(b);  
	    form.addButton(new Button("Cancel"));  
	  
	    form.setButtonAlign(HorizontalAlignment.CENTER);  
	  
	    FormButtonBinding binding = new FormButtonBinding(form);  
	    binding.addButton(b); 
		
		verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(20);
		verticalPanel.add(form);
		add(verticalPanel);
	}
	
	public static LayoutContainer getPanel() {
		return new ChangePasswordPanel();
	}

}
