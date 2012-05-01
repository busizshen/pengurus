package com.pengurus.crm.client.panels.center.user.create;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.PersonalDataModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class UserBasePanel extends MainPanel {

	private HorizontalPanel horizontalPanel;
	protected FormPanel form, additionalDataForm;
	protected PersonalDataEdit personalDataEdit;
	private ContentPanel agentsGridPanel, translationsGridPanel;
	private Grid<PersonalDataModel> agentsGrid;
	private EditorGrid<TranslationModel> translationsGrid;
	private FormData formData, addFormData;
	protected TextField<String> username, password, description, fullName;
	protected RadioGroup userType;
	protected CheckBoxGroup userRoles;
	protected UserFormButtonBinding binding;
	public ListStore<PersonalDataModel> personalDataStore = new ListStore<PersonalDataModel>();
	private ComboBox<TranslationModel> translationComboBox;
	public ListStore<TranslationModel> translationStore = new ListStore<TranslationModel>();
	final Window window = new Window();

	public UserBasePanel() {
		createForm();
		createUsernameField();
		createPasswordField();
		createDescriptionField();
		createUserTypeRatioGroup();
		createRoleCheckbox();
		createPersonalDataEdit();
		createAdditionalDataForm();
		createIndividualClientFullNameField();
		createAgentGrid();
		createWindow();
		createTranslationList();
		createButtons();
		addVerticalPanel();
	}


	protected abstract void createForm();


	private void addVerticalPanel() {
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(20);
		horizontalPanel.add(form);
		horizontalPanel.add(personalDataEdit);
		horizontalPanel.add(additionalDataForm);
		add(horizontalPanel);
	}

	private void createPersonalDataEdit() {
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

	protected void disableUserTypes() {
		userType.disable();
	}
	
	protected void selectUserType(UserType type) {
		for (Field<?> field: userType.getAll()) {
			if (field instanceof UserTypeRadio) {
				UserTypeRadio radio = (UserTypeRadio) field;
				if (radio.getType().equals(type)) {
					radio.setValue(true);
					radio.onSelect();
				}
			}
		}
	}
	
	private void disableAllRoles() {
		for (Field<?> field :userRoles.getAll()) {
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
	
	protected enum UserType {
		Worker, Translator, IndividualClient, BusinessClient;
	}
	
	protected abstract class UserTypeRadio extends Radio {
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
		
		public abstract UserType getType();

		protected void onSelect() {
			binding.getFields().add(username);
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
	
	protected abstract class ClientTypeRadio extends UserTypeRadio {
		public ClientTypeRadio(String label) {
			super(label);
		}
		
		@Override
		protected void onSelect() {
			super.onSelect();
			selectRole(UserRoleDTO.ROLE_CLIENT);
		}
	}

	protected class IndividualClientRadio extends ClientTypeRadio {
		public IndividualClientRadio() {
			super("Individual client");
		}

		@Override
		public UserType getType() {
			return UserType.IndividualClient;
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

	protected class BusinessClientRadio extends ClientTypeRadio {
		public BusinessClientRadio() {
			super("Business client");
		}

		@Override
		public UserType getType() {
			return UserType.BusinessClient;
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			additionalDataForm.show();
			fullName.show();
			binding.getFields().add(fullName);
			agentsGridPanel.show();
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			additionalDataForm.hide();
			fullName.hide();
			binding.getFields().remove(fullName);
			agentsGridPanel.hide();
		}

		@Override
		protected UserDTO createUser() {
			BusinessClientDTO businessClientDTO = new BusinessClientDTO();
			fillBasicUser(businessClientDTO);
			businessClientDTO.setName(fullName.getValue());
			Set<PersonalDataDTO> agents = new HashSet<PersonalDataDTO>();
			for (BaseModel model :personalDataStore.getModels()) {
				agents.add(((PersonalDataModel)model).getPersonalDataDTO());
			}
			businessClientDTO.setAgents(agents);
			return businessClientDTO;
		}
	}

	protected class WorkerRadio extends UserTypeRadio {
		public WorkerRadio() {
			super("Worker");
		}

		@Override
		public UserType getType() {
			return UserType.Worker;
		}
		
		public WorkerRadio(String label) {
			super(label);
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			enableRole(UserRoleDTO.ROLE_ACCOUNTANT);
			enableRole(UserRoleDTO.ROLE_EXECUTIVE);
			enableRole(UserRoleDTO.ROLE_PROJECTMANAGER);
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

	protected class TranslatorRadio extends WorkerRadio {
		public TranslatorRadio() {
			super("Translator");
		}

		@Override
		public UserType getType() {
			return UserType.Translator;
		}

		@Override
		protected void onSelect() {
			super.onSelect();
			selectRole(UserRoleDTO.ROLE_EXPERT);
			enableRole(UserRoleDTO.ROLE_VERIFICATOR);
			enableRole(UserRoleDTO.ROLE_FREELANCER);
			personalDataEdit.show();
			additionalDataForm.show();
			translationComboBox.show();
			translationsGridPanel.show();
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			translationsGridPanel.hide();
			translationComboBox.hide();
			additionalDataForm.hide();
			personalDataEdit.hide();
		}

		@Override
		protected UserDTO createUser() {
			TranslatorDTO translatorDTO = new TranslatorDTO();
			fillBasicUser(translatorDTO);
			translatorDTO.setPersonalData(personalDataEdit.toPersonalData());
			Set<TranslationDTO> translations = new HashSet<TranslationDTO>();
			for (TranslationModel translationModel: translationStore.getModels()) {
				translations.add(translationModel.getTranslationDTO());
			}
			translatorDTO.setTranslations(translations);
			return translatorDTO;
		}

	}

	private void createUserTypeRatioGroup() {
		userType = new RadioGroup("Usertype");
		userType.setFieldLabel("User type");
		userType.setOrientation(Orientation.VERTICAL);
		userType.add(new IndividualClientRadio());
		userType.add(new BusinessClientRadio());
		userType.add(new WorkerRadio());
		userType.add(new TranslatorRadio());
		form.add(userType, formData);
	}

	private class UserRoleBox extends CheckBox {
		private UserRoleDTO userRole;

		public UserRoleBox(String label, UserRoleDTO userRole) {
			super();
			setBoxLabel(label);
			this.userRole = userRole;
			this.setEnabled(false);
		}

		public UserRoleDTO getUserRole() {
			return userRole;
		}
	}

	private void createRoleCheckbox() {
		userRoles = new CheckBoxGroup();
		userRoles.setFieldLabel("User roles");
		userRoles.setOrientation(Orientation.VERTICAL);
		userRoles
				.add(new UserRoleBox("Role user", UserRoleDTO.ROLE_USER));
		userRoles.add(new UserRoleBox("Role client", UserRoleDTO.ROLE_CLIENT));
		userRoles.add(new UserRoleBox("Role expert", UserRoleDTO.ROLE_EXPERT));
		userRoles.add(new UserRoleBox("Role accountant",
				UserRoleDTO.ROLE_ACCOUNTANT));
		userRoles.add(new UserRoleBox("Role executive",
				UserRoleDTO.ROLE_EXECUTIVE));
		userRoles.add(new UserRoleBox("Role freelancer",
				UserRoleDTO.ROLE_FREELANCER));
		userRoles.add(new UserRoleBox("Role project manager",
				UserRoleDTO.ROLE_PROJECTMANAGER));
		userRoles.add(new UserRoleBox("Role verificator",
				UserRoleDTO.ROLE_VERIFICATOR));
		form.add(userRoles);
	}

	private void createIndividualClientFullNameField() {
		fullName = new TextField<String>();
		fullName.setFieldLabel("Full name");
		fullName.hide();
		fullName.setAllowBlank(false);
		additionalDataForm.add(fullName, addFormData);
	}

	private void createAgentGrid() {
		GridCellRenderer<PersonalDataModel> buttonRenderer = new GridCellRenderer<PersonalDataModel>() {

			private boolean init;

			public Object render(final PersonalDataModel model,
					String property, ColumnData config, final int rowIndex,
					final int colIndex,
					final ListStore<PersonalDataModel> store,
					Grid<PersonalDataModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<PersonalDataModel>>() {

								public void handleEvent(
										GridEvent<PersonalDataModel> be) {
									for (int i = 0; i < be.getGrid().getStore()
											.getCount(); i++) {
										if (be.getGrid().getView()
												.getWidget(i, be.getColIndex()) != null
												&& be.getGrid()
														.getView()
														.getWidget(
																i,
																be.getColIndex()) instanceof BoxComponent) {
											((BoxComponent) be
													.getGrid()
													.getView()
													.getWidget(i,
															be.getColIndex()))
													.setWidth(be.getWidth() - 10);
										}
									}
								}
							});
				}

				Button editButton = new Button("Edit",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								final PersonalDataEdit personalDataEdit = new PersonalDataEdit();
								personalDataEdit.fromPersonalData(model
										.getPersonalDataDTO());
								Button saveButton = new Button("Save",
										new SelectionListener<ButtonEvent>() {

											@Override
											public void componentSelected(
													ButtonEvent ce) {
												PersonalDataDTO newPersonalData = personalDataEdit
														.toPersonalData();
												store.remove(rowIndex);
												store.insert(
														new PersonalDataModel(
																newPersonalData),
														rowIndex);
												window.hide();
											}

										});
								Button removeButton = new Button("Remove",
										new SelectionListener<ButtonEvent>() {

											@Override
											public void componentSelected(
													ButtonEvent ce) {
												store.remove(rowIndex);
												window.hide();
											}

										});
								List<Button> buttonList = new ArrayList<Button>();
								buttonList.add(saveButton);
								buttonList.add(removeButton);
								personalDataPopup(personalDataEdit, buttonList);
							}
						});
				editButton.setWidth(grid.getColumnModel().getColumnWidth(
						colIndex) - 10);
				editButton.setToolTip("Click to edit");

				return editButton;
			}
		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("firstName");
		column.setHeader("First name");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("lastName");
		column.setHeader("Last name");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("edit");
		column.setHeader("edit");
		column.setWidth(70);
		column.setRenderer(buttonRenderer);
		configs.add(column);

		personalDataStore = new ListStore<PersonalDataModel>();

		ColumnModel cm = new ColumnModel(configs);

		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final PersonalDataEdit personalDataEdit = new PersonalDataEdit();
				personalDataEdit.fromPersonalData(new PersonalDataDTO());
				Button saveButton = new Button("Save",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								PersonalDataDTO newPersonalData = personalDataEdit
										.toPersonalData();
								personalDataStore.insert(new PersonalDataModel(
										newPersonalData), personalDataStore.getCount());
								personalDataStore.commitChanges();
								window.hide();
							}

						});
				List<Button> buttonList = new ArrayList<Button>();
				buttonList.add(saveButton);
				personalDataPopup(personalDataEdit, buttonList);
			}
		});
		toolBar.add(add);

		agentsGridPanel = new ContentPanel();
		agentsGridPanel.setBodyBorder(false);
		agentsGridPanel.setHeading("Agents");
		agentsGridPanel.setButtonAlign(HorizontalAlignment.CENTER);
		agentsGridPanel.setLayout(new FitLayout());
		agentsGridPanel.setSize(280, 200);
		agentsGridPanel.setTopComponent(toolBar);

		agentsGrid = new Grid<PersonalDataModel>(personalDataStore, cm);
		agentsGrid.setStyleAttribute("borderTop", "none");
		agentsGrid.setAutoExpandColumn("firstName");
		agentsGrid.setBorders(true);
		agentsGridPanel.add(agentsGrid);
		agentsGridPanel.hide();

		additionalDataForm.add(agentsGridPanel, addFormData);
	}

	private void personalDataPopup(PersonalDataEdit personalDataEdit,
			List<Button> buttonList) {
		window.removeAll();
		window.add(personalDataEdit);
		Button cancelButton = new Button("Cancel",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.hide();
					}
				});
		buttonList.add(cancelButton);
		for (Button button : buttonList) {
			personalDataEdit.addButton(button);
		}
		window.show();
	}

	private void createAdditionalDataForm() {
		additionalDataForm = new FormPanel();
		additionalDataForm.setHeading("Additional data");
		additionalDataForm.setFrame(true);
		additionalDataForm.setLabelAlign(LabelAlign.TOP);
		additionalDataForm.hide();
	}
	
	private void createWindow() {
		window.setPlain(true);
		window.setHeading("Edit personal data");
		window.setLayout(new FlowLayout());
		window.setAutoHeight(true);
		window.setAutoWidth(true);
		window.setAutoHide(true);
	}

	private void createTranslationList() {

		translationComboBox = new ComboBox<TranslationModel>();
		translationComboBox.setFieldLabel("Translation to add");
		final ListStore<TranslationModel> translationComboStore = new ListStore<TranslationModel>();
		translationComboBox.setStore(translationComboStore);
		translationComboBox.setForceSelection(true);
		translationComboBox.setWidth(400);
		translationComboBox.setDisplayField("name");
		translationComboBox.setEmptyText("Select translation");
		translationComboBox.setTriggerAction(TriggerAction.ALL);
		
		AsyncCallback<Set<TranslationDTO>> callback = new AsyncCallback<Set<TranslationDTO>>() {
			
			@Override
			public void onSuccess(Set<TranslationDTO> result) {
				for (TranslationDTO translationDTO: result) {
					translationComboStore.add(new TranslationModel(translationDTO));
				}
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
		
		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getTranslations(callback);
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		final CheckBoxSelectionModel<TranslationModel> checkBoxSelection = new CheckBoxSelectionModel<TranslationModel>();
		configs.add(checkBoxSelection.getColumn());
		
		ColumnConfig column = new ColumnConfig();
		
        column.setId("from");
        column.setHeader("From");
        column.setWidth(80);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("to");
        column.setHeader("To");
        column.setWidth(80);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("type");
        column.setHeader("Translation type");
        column.setWidth(80);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("defaultPrice");
        column.setHeader("Price");
        column.setWidth(50);
        column.setAlignment(HorizontalAlignment.CENTER);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("defaultPriceCurrency");
        column.setHeader("Currency");
        column.setWidth(70);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

		translationStore = new ListStore<TranslationModel>();

		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add translation type", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				translationsGrid.stopEditing();
		        translationStore.insert(translationComboBox.getValue(), translationStore.getCount());  
		        translationsGrid.startEditing(translationStore.getCount() - 1, 0);
			}
		});
		toolBar.add(add);
		Button remove = new Button("Remove");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				translationsGrid.stopEditing();
				for (TranslationModel translationModel : checkBoxSelection
						.getSelectedItems()) {
					translationStore.remove(translationModel);
				}
				translationsGrid.startEditing(0, 0);
			}

		});
		toolBar.add(remove);
		
		translationsGridPanel = new ContentPanel();
		translationsGridPanel.setBodyBorder(false);
		translationsGridPanel.setHeading("Translations");
		translationsGridPanel.setButtonAlign(HorizontalAlignment.CENTER);
		translationsGridPanel.setLayout(new FitLayout());
		translationsGridPanel.setSize(400, 200);
		translationsGridPanel.setTopComponent(toolBar);

		translationsGrid = new EditorGrid<TranslationModel>(translationStore, cm);
		translationsGrid.setStyleAttribute("borderTop", "none");
		translationsGrid.setBorders(true);
		translationsGrid.setSelectionModel(checkBoxSelection);
		translationsGrid.addPlugin(checkBoxSelection);
		translationsGridPanel.add(translationsGrid);
		translationsGridPanel.hide();
		
		additionalDataForm.add(translationComboBox, addFormData);
		additionalDataForm.add(translationsGridPanel, addFormData);
	}

	protected abstract void createButtons();

	protected List<Field<?>> getBaseRequiredFields() {
		List<Field<?>> fields = new ArrayList<Field<?>>();
		fields.add(userType);
		fields.add(username);
		fields.add(password);
		return fields;
	}
}
