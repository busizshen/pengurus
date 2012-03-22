package com.pengurus.crm.client.panels.center.user.create;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.shared.dto.UserDTO;

public class UserCreatePanel extends UserBasePanel {

	private Button createButton;

	@Override
	protected void createButtons() {
		binding = new UserFormButtonBinding(this);
		binding.getFields().addAll(getBaseRequiredFields());
		createButton = new Button("Create",
				new SelectionListener<ButtonEvent>() {
	
					@Override
					public void componentSelected(ButtonEvent ce) {
						AsyncCallback<Void> callback = new AsyncCallback<Void>() {
							
							@Override
							public void onSuccess(Void result) {
								MessageBox.info("Success", "Your have succesfully created new user.", null);
							}
							
							@Override
							public void onFailure(Throwable caught) {
								if (caught instanceof UsernameExistsException) {
									MessageBox.info("Failure", "Chosen username already exists.", null);
								} else {
									MessageBox.info("Failure", "Creating new user has failed.", null);
								}
							}
						};
						
						UserDTO user = ((UserTypeRadio)userType.getValue()).createUser();
						UserServiceAsync service = (UserServiceAsync) GWT
								.create(UserService.class);
						service.createUser(user, callback);
					}
				});
	
		binding.addButton(createButton);
		form.addButton(createButton);
	}

}
