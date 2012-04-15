package com.pengurus.crm.client.panels.center.user.create;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.PersonalDataModel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class UserCreatePanel extends UserBasePanel {

	private Button createButton;
	private UserFormButtonBinding binding;
	private ListStore<PersonalDataModel> store = new ListStore<PersonalDataModel>();
	final Window window = new Window();

	public UserCreatePanel() {
		createForm();
		createUsernameField();
		createPasswordField();
		createDescriptionField();
		createUserTypeRatioGroup();
		createRoleCheckbox();
		createIndividualClientFullNameField();
		createPersonalDataForm();
		createAgentGrid();
		createWindow();
		createButtons();
		addVerticalPanel();
	}

	protected RadioGroup getUserType() {
		return userType;
	}

	protected void setUserType(RadioGroup userType) {
		this.userType = userType;
	}

	private void addVerticalPanel() {
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(20);
		horizontalPanel.add(form);
		horizontalPanel.add(personalDataEdit);
		add(horizontalPanel);
	}

	protected void createForm() {
		form = new FormPanel();
		form.setHeading("Create user");
		form.setFrame(true);
		form.setPadding(25);
		form.setLabelAlign(LabelAlign.TOP);
	}

	private void createPersonalDataForm() {
		personalDataEdit = new PersonalDataEdit();
		personalDataEdit.setHeading("Personal data");
		personalDataEdit.setFrame(true);
		personalDataEdit.setLabelAlign(LabelAlign.TOP);
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
						UserTypeRadio radio = (UserTypeRadio) userType.getAll()
								.get(i);
						radio.onDeselect();
					}
					onSelect();
				}

			});
		}
		
		private void disableAllRoles() {
			for (Field<?> field : userRoles.getAll()) {
				if (field instanceof UserRoleBox) {
					UserRoleBox userRoleBox = (UserRoleBox) field;
					userRoleBox.setEnabled(false);
				}
			}
		}
		
		private void setRoleEnable(UserRoleDTO userRole, Boolean value) {
			for (Field<?> field : userRoles.getAll()) {
				if (field instanceof UserRoleBox) {
					UserRoleBox userRoleBox = (UserRoleBox) field;
					if (userRoleBox.getUserRole().equals(userRole)) {
						userRoleBox.setEnabled(value);
					}
				}
			}
		}
		
		protected void enableRole(UserRoleDTO userRole) {
			setRoleEnable(userRole, true);
		}
		
		@SuppressWarnings("unused")
		protected void disableRole(UserRoleDTO userRole) {
			setRoleEnable(userRole, false);
		}
		
		
		protected void deselectAllRoles() {
			for (Field<?> field: userRoles.getAll()) {
				if (field instanceof UserRoleBox) {
					UserRoleBox userRoleBox = (UserRoleBox) field;
					userRoleBox.setValue(false);
				}
			}
		}
		
		private void setRoleValue(UserRoleDTO userRole, Boolean value) {
			for (Field<?> field : userRoles.getAll()) {
				if (field instanceof UserRoleBox) {
					UserRoleBox userRoleBox = (UserRoleBox) field;
					if (userRoleBox.getUserRole().equals(userRole)) {
						userRoleBox.setValue(value);
					}
				}
			}
		}

		protected void selectRole(UserRoleDTO userRole) {
			setRoleValue(userRole, true);
		}

		protected void deselectRole(UserRoleDTO userRole) {
			setRoleValue(userRole, false);
		}

		protected void onSelect() {
			selectRole(UserRoleDTO.ROLE_USER);
		}
		
		protected void onDeselect() {
			deselectAllRoles();
			disableAllRoles();
		}
		
		protected abstract UserDTO createUser();
		protected void fillBasicUser(UserDTO user) {
			user.setUsername(username.getValue());
			user.setPassword(password.getValue());
			user.setDescription(description.getValue());
			Set<UserRoleDTO> authorities = new HashSet<UserRoleDTO>();
			for (CheckBox checkBox: userRoles.getValues()) {
				authorities.add(((UserRoleBox)checkBox).getUserRole());
			}
			user.setAuthorities(authorities);
		}
	}
	
	private abstract class ClientTypeRadio extends UserTypeRadio {
		public ClientTypeRadio(String label) {
			super(label);
		}
		
		@Override
		protected void onSelect() {
			super.onSelect();
			selectRole(UserRoleDTO.ROLE_CLIENT);
		}
	}

	private class IndividualClientRadio extends ClientTypeRadio {
		public IndividualClientRadio() {
			super("Individual client");
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			personalDataEdit.show();
			for (Field<?> field: personalDataEdit.getFields()) {
				binding.getFields().add(field);
			}
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			for (Field<?> field: personalDataEdit.getFields()) {
				binding.getFields().remove(field);
			}
		}

		@Override
		protected UserDTO createUser() {
			IndividualClientDTO user = new IndividualClientDTO();
			fillBasicUser(user);
			user.setPersonalData(personalDataEdit.toPersonalData());
			return user;
		}
	}

	private class BusinessClientRadio extends ClientTypeRadio {
		public BusinessClientRadio() {
			super("Business client");
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			fullName.show();
			binding.getFields().add(fullName);
			agentsGrid.show();
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			fullName.hide();
			binding.getFields().remove(fullName);
			agentsGrid.hide();
		}

		@Override
		protected UserDTO createUser() {
			BusinessClientDTO businessClientDTO = new BusinessClientDTO();
			fillBasicUser(businessClientDTO);
			businessClientDTO.setName(fullName.getValue());
			Set<PersonalDataDTO> agents = new HashSet<PersonalDataDTO>();
			for (BaseModel model :store.getModels()) {
				agents.add(((PersonalDataModel)model).getPersonalDataDTO());
			}
			businessClientDTO.setAgents(agents);
			return businessClientDTO;
		}
	}

	private class WorkerRadio extends UserTypeRadio {
		public WorkerRadio() {
			super("Worker");
		}
		
		public WorkerRadio(String label) {
			super(label);
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			enableRole(UserRoleDTO.ROLE_ACCOUNTANT);
			enableRole(UserRoleDTO.ROLE_EXECUTIVE);
			enableRole(UserRoleDTO.ROLE_PROJECTMNAGER);
			personalDataEdit.show();
			for (Field<?> field: personalDataEdit.getFields()) {
				binding.getFields().add(field);
			}
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			personalDataEdit.hide();
			for (Field<?> field: personalDataEdit.getFields()) {
				binding.getFields().remove(field);
			}

		}

		@Override
		protected UserDTO createUser() {
			WorkerDTO workerDTO = new WorkerDTO();
			fillBasicUser(workerDTO);
			workerDTO.setPersonalData(personalDataEdit.toPersonalData());
			return workerDTO;
		}
	}

	private class TranslatorRadio extends WorkerRadio {
		public TranslatorRadio() {
			super("Translator");
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			enableRole(UserRoleDTO.ROLE_EXPERT);
			enableRole(UserRoleDTO.ROLE_VERIFICATOR);
			enableRole(UserRoleDTO.ROLE_FREELANCER);
			personalDataEdit.show();
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			personalDataEdit.hide();
		}

		@Override
		protected UserDTO createUser() {
			return null;
		}

	}

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
