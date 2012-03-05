package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.client.service.CurrentSessionServiceAsync;
import com.pengurus.crm.client.service.exceptions.IncorrectPasswordException;

public class ChangePasswordPanel extends MainPanel {

	private VerticalPanel verticalPanel;
	private FormData formData;
	private FormPanel form;
	private TextField<String> currentPassword, newPassword, confirmedPassword;

	public ChangePasswordPanel() {
		createForm();
		addOldPasswordField();
		addNewPasswordField();
		addConfirmedPasswordField();
		addButtons();
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
		form.setHeading("Change Password");
		form.setFrame(true);
		form.setLabelAlign(LabelAlign.TOP);
	}

	private void addOldPasswordField() {
		currentPassword = new TextField<String>();
		currentPassword.setPassword(true);
		currentPassword.setFieldLabel("Current password");
		currentPassword.setAllowBlank(false);
		form.add(currentPassword, formData);
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
				if (!value.matches(".*[0-9].*")) {
					return "Password must contain at least one number";
				}
				if (!value.matches(".*[a-zA-Z].*")) {
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
		b.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						MessageBox.info("Success", "Your password was sucessfully changed.", null);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof IncorrectPasswordException) {
							MessageBox.info("Failure", "The password you have entered does not match your current one.", null);
						}
					}
				};
				CurrentSessionServiceAsync service = (CurrentSessionServiceAsync) GWT
						.create(CurrentSessionService.class);
				service.setPassword(currentPassword.getValue(), newPassword.getValue(), callback);
			}
		});
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
