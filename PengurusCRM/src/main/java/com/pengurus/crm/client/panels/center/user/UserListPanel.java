package com.pengurus.crm.client.panels.center.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class UserListPanel extends BaseUsersListPanel<UserModel> {

	static UserListPanel instance;

	private LayoutContainer sideOptions;
	private CheckBoxGroup userRoles;
	private CheckBox allBox;

	public static UserListPanel getIntance() {
		if (instance == null) {
			instance = new UserListPanel();
		}
		return instance;
	}

	public UserListPanel() {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(10);
		horizontalPanel.add(new ModelList());
		sideOptions = new LayoutContainer();
		createRoleCheckBoxGroup();
		createRefreshButton();
		horizontalPanel.add(sideOptions);
		add(horizontalPanel);
	}

	private class UserRoleBox extends CheckBox {
		private UserRoleDTO userRole;

		public UserRoleBox(String label, UserRoleDTO userRole) {
			super();
			setBoxLabel(label);
			this.userRole = userRole;
		}

		public UserRoleDTO getUserRole() {
			return userRole;
		}
	}

	private void createRoleCheckBoxGroup() {
		userRoles = new CheckBoxGroup();
		userRoles.setOrientation(Orientation.VERTICAL);
		allBox = new CheckBox();
		allBox.setBoxLabel("All users");
		userRoles.add(allBox);
		userRoles.add(new UserRoleBox("Role user", UserRoleDTO.ROLE_USER));
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
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("User roles");
		fieldSet.add(userRoles);
		sideOptions.add(fieldSet);
	}
	
	private void deselectAllRoles() {
		for (Field<?> field: userRoles.getAll()) {
			if (field instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) field;
				checkBox.setValue(false);
			}
		}
	}
	
	public void selectAll() {
		deselectAllRoles();
		allBox.setValue(true);
	}
	
	public void selectRole(UserRoleDTO role) {
		deselectAllRoles();
		for (Field<?> field: userRoles.getAll()) {
			if (field instanceof UserRoleBox) {
				UserRoleBox userRoleBox = (UserRoleBox) field;
				if (userRoleBox.getUserRole().equals(role)) {
					userRoleBox.setValue(true);	
				}
			}
		}
	}

	public void refreshList() {
		AsyncCallback<Set<UserDTO>> callback = new AsyncCallback<Set<UserDTO>>() {
			
			@Override
			public void onFailure(Throwable caught) {
					MessageBox.info("Failure", "Server error.", null);
			}

			@Override
			public void onSuccess(Set<UserDTO> userSet) {
				getGrid().stopEditing();
				getStore().removeAll();
				ArrayList<UserModel> userModelList = new ArrayList<UserModel>();
				for (UserDTO user: userSet) {
					userModelList.add(new UserModel(user));
				}
				getStore().add(userModelList);
				getGrid().startEditing(0, 0);
			}
		};
		if (allBox.getValue()) {
			UserServiceAsync service = (UserServiceAsync) GWT
					.create(UserService.class);
			service.getAllUsers(callback);
		} else {
			Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
			for (Field<?> field: userRoles.getAll()) {
				if (field instanceof UserRoleBox) {
					UserRoleBox roleBox = (UserRoleBox) field;
					if (roleBox.getValue()) {
						roles.add(roleBox.getUserRole());
					}
				}
			}
			UserServiceAsync service = (UserServiceAsync) GWT
					.create(UserService.class);
			service.getUsersByRoles(roles, callback);
		}
	}
	
	private void createRefreshButton() {
		Button refreshButton = new Button("Refresh", new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				refreshList();
			}
		});
		sideOptions.add(refreshButton);
	}
	
	@Override
	protected GridCellRenderer<UserModel> getButtonRenderer() {
		GridCellRenderer<UserModel> buttonRenderer = new GridCellRenderer<UserModel>() {

			private boolean init;

			public Object render(final UserModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<UserModel> store, Grid<UserModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<UserModel>>() {

								public void handleEvent(GridEvent<UserModel> be) {
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
								MessageBox.info("a", model.getUserDTO()
										.getUsername(), null);
							}
						});

				editButton.setWidth(grid.getColumnModel().getColumnWidth(
						colIndex) - 10);
				editButton.setToolTip("Click to edit");
				return editButton;
			}
		};
		return buttonRenderer;
	}

	@Override
	protected String getName() {
		return "User list";
	}

	@Override
	protected ListStore<UserModel> getList() {
		return new ListStore<UserModel>();
	}

}
