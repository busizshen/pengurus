package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;

public class UserCreatePanel extends LayoutContainer {
	
	private VerticalPanel verticalPanel;
	private FormPanel form;
	private FormData formData;
	private TextField<String> username, password, description, fullName;
	private RadioGroup userType;
	
	public UserCreatePanel() {
		createForm();
		createUsernameField();
		createPasswordField();
		createDescriptionField();
		createUserTypeRatioGroup();
		createIndividualClientFullNameField();
		//createRoleCheckbox();
		addVerticalPanel();
	}

	private void addVerticalPanel() {
		verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(20);
		verticalPanel.add(form);
		add(verticalPanel);
	}

	private void createForm() {
		form = new FormPanel();
		form.setHeading("Create user");
		form.setFrame(true);
		form.setLabelAlign(LabelAlign.TOP);
	}

	private void createUsernameField() {
		username = new TextField<String>();
		username.setFieldLabel("Username");
		username.setAllowBlank(false);
		form.add(username, formData);
	}

	private void createPasswordField() {
		password = new TextField<String>();
		password.setFieldLabel("Password");
		password.setPassword(true);
		password.setAllowBlank(false);
		form.add(password, formData);
	}

	private void createDescriptionField() {
		description = new TextField<String>();
		description.setFieldLabel("Description");
		form.add(description, formData);
	}

	private abstract class UserTypeRadio extends Radio {
		public UserTypeRadio(String label) {
			super();
			setBoxLabel(label);
			addListener(Events.OnClick, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					for (int i = 0; i < userType.getAll().size(); ++i) {
						UserTypeRadio radio = (UserTypeRadio)userType.getAll().get(i);
						radio.onDeselect();
					}
					onSelect();
				}
				
			});
		}
		protected abstract void onSelect();
		protected abstract void onDeselect();
	}
	
	private class IndividualClientRadio extends UserTypeRadio {
		public IndividualClientRadio() {
			super("Individual client");
		}
		
		@Override
		protected void onSelect() {
			fullName.show();
		}

		@Override
		protected void onDeselect() {
			fullName.hide();
		}	
	}
	
	private class BusinessClientRadio extends UserTypeRadio {
		public BusinessClientRadio() {
			super("Business client");
		}

		@Override
		protected void onSelect() {
			// TODO Auto-generated method stub
		}

		@Override
		protected void onDeselect() {
			// TODO Auto-generated method stub
		}
	}

	private void createIndividualClientFullNameField() {
		fullName = new TextField<String>();
		fullName.setFieldLabel("Full name");
		fullName.hide();
		form.add(fullName, formData);
	}
	
	private void createUserTypeRatioGroup() {
		userType = new RadioGroup("Usertype");
		userType.setFieldLabel("User type");
		userType.add(new IndividualClientRadio());
		userType.add(new BusinessClientRadio());
		form.add(userType, formData);
	}

	
}
