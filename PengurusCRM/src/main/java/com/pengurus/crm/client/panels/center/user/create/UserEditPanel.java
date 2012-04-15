package com.pengurus.crm.client.panels.center.user.create;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.PersonalDataModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.ClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class UserEditPanel extends UserBasePanel {

	private long id;
	private Button saveButton;

	public UserEditPanel(UserDTO user) {
		super();
		userType.disable();
		initUser(user);
	}

	private void initUser(UserDTO user) {

		if (user instanceof WorkerDTO) {
			initWorker((WorkerDTO) user);
		} else if (user instanceof ClientDTO) {
			initClient((ClientDTO) user);
		}

		for (UserRoleDTO userRole : user.getAuthorities()) {
			selectRole(userRole);
		}

		id = user.getId();
		username.setValue(user.getUsername());
		description.setValue(user.getDescription());
	}

	private void initWorker(WorkerDTO worker) {
		if (worker instanceof TranslatorDTO) {
			initTranslator((TranslatorDTO) worker);
		} else {
			selectUserType(UserType.Worker);
		}
		personalDataEdit.fromPersonalData(worker.getPersonalData());
	}

	private void initTranslator(TranslatorDTO translator) {
		selectUserType(UserType.Translator);
		for (TranslationDTO translationDTO: translator.getTranslations()) {
			translationStore.insert(new TranslationModel(translationDTO), translationStore.getCount());
		}
	}

	private void initClient(ClientDTO client) {
		if (client instanceof IndividualClientDTO) {
			initIndividualClient((IndividualClientDTO) client);
		} else {
			initBusinessClient((BusinessClientDTO) client);
		}
	}

	private void initIndividualClient(IndividualClientDTO client) {
		selectUserType(UserType.IndividualClient);
		personalDataEdit.fromPersonalData(client.getPersonalData());
	}

	private void initBusinessClient(BusinessClientDTO client) {
		selectUserType(UserType.BusinessClient);
		for (PersonalDataDTO personalDataDTO : client.getAgents()) {
			personalDataStore.insert(new PersonalDataModel(personalDataDTO), personalDataStore.getCount());
		}
	}

	@Override
	protected void createButtons() {
		binding = new UserFormButtonBinding(this);
		binding.getFields().addAll(getBaseRequiredFields());
		saveButton = new Button("Save", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						MessageBox.info("Success",
								"Your have succesfully updated user data.",
								null);
					}

					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof UsernameExistsException) {
							MessageBox.info("Failure",
									"Chosen username already exists.", null);
						} else {
							MessageBox.info("Failure",
									"Updating user has failed.", null);
						}
					}
				};

				UserDTO user = ((UserTypeRadio) userType.getValue())
						.createUser();
				user.setId(id);
				if ((password.getValue() == null) || (password.getValue().isEmpty())) {
					UserServiceAsync service = (UserServiceAsync) GWT
							.create(UserService.class);
					service.updateUser(user, callback);
				} else {
					UserServiceAsync service = (UserServiceAsync) GWT
							.create(UserService.class);
					service.updateUserWithPassword(user, callback);
				}
			}
		});

		binding.addButton(saveButton);
		form.addButton(saveButton);
	}

	@Override
	protected List<Field<?>> getBaseRequiredFields() {
		List<Field<?>> fields = new ArrayList<Field<?>>();
		fields.add(userType);
		fields.add(username);
		return fields;
	}

	protected void createForm() {
		form = new FormPanel();
		form.setHeading("Edit user");
		form.setFrame(true);
		form.setPadding(25);
		form.setLabelAlign(LabelAlign.TOP);
	}
}
