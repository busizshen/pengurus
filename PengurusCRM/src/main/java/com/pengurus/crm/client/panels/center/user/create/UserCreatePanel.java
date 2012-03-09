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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.pengurus.crm.client.models.PersonalDataModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class UserCreatePanel extends MainPanel {

	private HorizontalPanel horizontalPanel;
	private FormPanel form;
	private PersonalDataForm personalDataForm;
	private ContentPanel agentsGrid;
	private Grid<PersonalDataModel> grid;
	private FormData formData;
	private TextField<String> username, password, description, fullName;
	private RadioGroup userType;
	private CheckBoxGroup userRoles;
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
		horizontalPanel.add(personalDataForm);
		add(horizontalPanel);
	}

	private void createForm() {
		form = new FormPanel();
		form.setHeading("Create user");
		form.setFrame(true);
		form.setPadding(25);
		form.setLabelAlign(LabelAlign.TOP);
	}

	private void createPersonalDataForm() {
		personalDataForm = new PersonalDataForm();
		personalDataForm.setHeading("Personal data");
		personalDataForm.setFrame(true);
		personalDataForm.setLabelAlign(LabelAlign.TOP);
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
			personalDataForm.show();
			for (Field<?> field: personalDataForm.getFields()) {
				binding.getFields().add(field);
			}
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			for (Field<?> field: personalDataForm.getFields()) {
				binding.getFields().remove(field);
			}
		}

		@Override
		protected UserDTO createUser() {
			IndividualClientDTO user = new IndividualClientDTO();
			fillBasicUser(user);
			user.setPersonalData(personalDataForm.toPersonalData());
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
			personalDataForm.show();
			for (Field<?> field: personalDataForm.getFields()) {
				binding.getFields().add(field);
			}
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			personalDataForm.hide();
			for (Field<?> field: personalDataForm.getFields()) {
				binding.getFields().remove(field);
			}

		}

		@Override
		protected UserDTO createUser() {
			WorkerDTO workerDTO = new WorkerDTO();
			fillBasicUser(workerDTO);
			workerDTO.setPersonalData(personalDataForm.toPersonalData());
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
			personalDataForm.show();
		}

		@Override
		protected void onDeselect() {
			super.onDeselect();
			personalDataForm.hide();
		}

		@Override
		protected UserDTO createUser() {
			return null;
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
				UserRoleDTO.ROLE_PROJECTMNAGER));
		userRoles.add(new UserRoleBox("Role verificator",
				UserRoleDTO.ROLE_VERIFICATOR));
		form.add(userRoles);
	}

	private void createIndividualClientFullNameField() {
		fullName = new TextField<String>();
		fullName.setFieldLabel("Full name");
		fullName.hide();
		fullName.setAllowBlank(false);
		form.add(fullName, formData);
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
								final PersonalDataForm personalDataForm = new PersonalDataForm();
								personalDataForm.fromPersonalData(model
										.getPersonalDataDTO());
								Button saveButton = new Button("Save",
										new SelectionListener<ButtonEvent>() {

											@Override
											public void componentSelected(
													ButtonEvent ce) {
												PersonalDataDTO newPersonalData = personalDataForm
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
								personalDataPopup(personalDataForm, buttonList);
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

		store = new ListStore<PersonalDataModel>();
		// TODO - store.add(TestData.getStocks());

		ColumnModel cm = new ColumnModel(configs);

		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final PersonalDataForm personalDataForm = new PersonalDataForm();
				personalDataForm.fromPersonalData(new PersonalDataDTO());
				Button saveButton = new Button("Save",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								PersonalDataDTO newPersonalData = personalDataForm
										.toPersonalData();
								store.insert(new PersonalDataModel(
										newPersonalData), store.getCount());
								store.commitChanges();
								window.hide();
							}

						});
				List<Button> buttonList = new ArrayList<Button>();
				buttonList.add(saveButton);
				personalDataPopup(personalDataForm, buttonList);
			}
		});
		toolBar.add(add);

		agentsGrid = new ContentPanel();
		agentsGrid.setBodyBorder(false);
		agentsGrid.setHeading("Agents");
		agentsGrid.setButtonAlign(HorizontalAlignment.CENTER);
		agentsGrid.setLayout(new FitLayout());
		agentsGrid.setSize(280, 200);
		agentsGrid.setTopComponent(toolBar);

		grid = new Grid<PersonalDataModel>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("firstName");
		grid.setBorders(true);
		agentsGrid.add(grid);
		agentsGrid.hide();

		form.add(agentsGrid);
	}

	private void personalDataPopup(PersonalDataForm personalDataForm,
			List<Button> buttonList) {
		window.removeAll();
		window.add(personalDataForm);
		Button cancelButton = new Button("Cancel",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.hide();
					}
				});
		buttonList.add(cancelButton);
		for (Button button : buttonList) {
			personalDataForm.addButton(button);
		}
		window.show();
	}

	private void createWindow() {
		window.setPlain(true);
		window.setHeading("Edit personal data");
		window.setLayout(new FlowLayout());
		window.setAutoHeight(true);
		window.setAutoWidth(true);
		window.setAutoHide(true);
	}

	private void createButtons() {
		binding = new UserFormButtonBinding(this);
		for (Field<?> field : form.getFields()) {
			binding.getFields().add(field);
		}
		for (Field<?> field : personalDataForm.getFields()) {
			binding.getFields().add(field);
		}
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
