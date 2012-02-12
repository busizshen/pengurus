package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FormData;

public class ChangePasswordPanel extends LayoutContainer {

	private VerticalPanel verticalPanel;
	private FormData formData;
	private FormPanel form;
	private TextField<String> oldPassword, newPassword, confirmedPassword;

	private ChangePasswordPanel() {
		form = new FormPanel();
		form.setHeading("Change Password");
		form.setFrame(true);
		form.setLabelAlign(LabelAlign.TOP);

		addOldPasswordField();
		addNewPasswordField();
		addConfirmedPasswordField();
		addButtons();

		verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(20);
		verticalPanel.add(form);
		add(verticalPanel);
	}

	private void addOldPasswordField() {
		oldPassword = new TextField<String>();
		oldPassword.setPassword(true);
		oldPassword.setFieldLabel("Current password");
		oldPassword.setAllowBlank(false);
		form.add(oldPassword, formData);
	}

	private void addNewPasswordField() {
		newPassword = new TextField<String>();
		newPassword.setPassword(true);
		newPassword.setFieldLabel("New password");
		newPassword.setAllowBlank(false);
		newPassword.setValidator(new Validator() {

			@Override
			public String validate(Field<?> field, String value) {
				if (value.length() < 8) {
					return "Password must be at least 8 characters long";
				}
				if (value.matches(".*[0-9].*")) {
					return "Password must contain at least one number";
				}
				if (value.matches(".*[a-zA-Z].*")) {
					return "Password must contain at least one letter";
				}
				return null;
			}
		});
		form.add(newPassword, formData);
	}

	private void addConfirmedPasswordField() {
		confirmedPassword = new TextField<String>();
		confirmedPassword.setPassword(true);
		confirmedPassword.setFieldLabel("Confirm new password");
		confirmedPassword.setAllowBlank(false);
		confirmedPassword.setValidator(new Validator() {

			@Override
			public String validate(Field<?> field, String value) {
				if (!value.equals(newPassword.getValue())) {
					return "The password and confirmation doesn't match";
				}
				return null;
			}
		});
		form.add(confirmedPassword, formData);
	}

	private void addButtons() {
		Button b = new Button("Submit");
		form.addButton(b);
		form.addButton(new Button("Cancel"));
		form.setButtonAlign(HorizontalAlignment.CENTER);
		FormButtonBinding binding = new FormButtonBinding(form);
		binding.addButton(b);
	}

	public static LayoutContainer getPanel() {
		return new ChangePasswordPanel();
	}

}
