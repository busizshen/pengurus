package com.pengurus.crm.client.panels.center;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class UserCreatePanel extends LayoutContainer {

	private VerticalPanel verticalPanel;
	private FormPanel form;
	private FormData formData;
	private TextField<String> username, password, description, fullName;
	private RadioGroup userType;
	private CheckBoxGroup userRoles;

	public UserCreatePanel() {
		createForm();
		createUsernameField();
		createPasswordField();
		createDescriptionField();
		createUserTypeRatioGroup();
		createRoleCheckbox();
		createIndividualClientFullNameField();
		addVerticalPanel();
	}

	protected RadioGroup getUserType() {
		return userType;
	}

	protected void setUserType(RadioGroup userType) {
		this.userType = userType;
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
						UserTypeRadio radio = (UserTypeRadio) userType.getAll()
								.get(i);
						radio.onDeselect();
					}
					onSelect();
				}

			});
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

		protected abstract void onSelect();
		protected abstract void onDeselect();
	}

	private class IndividualClientRadio extends UserTypeRadio {
		public IndividualClientRadio() {
			super("Individual client");
		}

		@Override
		protected void onSelect() {
			selectRole(UserRoleDTO.ROLE_USER);
			selectRole(UserRoleDTO.ROLE_CLIENT);
			fullName.show();
		}

		@Override
		protected void onDeselect() {
			deselectRole(UserRoleDTO.ROLE_USER);
			deselectRole(UserRoleDTO.ROLE_CLIENT);
			fullName.hide();
		}
	}

	private class BusinessClientRadio extends UserTypeRadio {
		public BusinessClientRadio() {
			super("Business client");
		}

		@Override
		protected void onSelect() {
			selectRole(UserRoleDTO.ROLE_USER);
			selectRole(UserRoleDTO.ROLE_CLIENT);
		}

		@Override
		protected void onDeselect() {
			deselectRole(UserRoleDTO.ROLE_USER);
			deselectRole(UserRoleDTO.ROLE_CLIENT);
		}
	}

	private class WorkerRadio extends UserTypeRadio {
		public WorkerRadio() {
			super("Worker");
		}

		@Override
		protected void onSelect() {
			selectRole(UserRoleDTO.ROLE_USER);

		}

		@Override
		protected void onDeselect() {
			deselectRole(UserRoleDTO.ROLE_USER);

		}
	}

	private class TranslatorRadio extends UserTypeRadio {
		public TranslatorRadio() {
			super("Translator");
		}

		@Override
		protected void onSelect() {
			selectRole(UserRoleDTO.ROLE_USER);
			selectRole(UserRoleDTO.ROLE_EXPERT);

		}

		@Override
		protected void onDeselect() {
			deselectRole(UserRoleDTO.ROLE_USER);
			deselectRole(UserRoleDTO.ROLE_EXPERT);

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

		public UserRoleBox(String label, UserRoleDTO userRole, Boolean enabled) {
			super();
			setBoxLabel(label);
			this.userRole = userRole;
			this.setEnabled(enabled);
		}

		public UserRoleBox(String label, UserRoleDTO userRole) {
			this(label, userRole, true);
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
				.add(new UserRoleBox("Role user", UserRoleDTO.ROLE_USER, false));
		userRoles.add(new UserRoleBox("Role client", UserRoleDTO.ROLE_CLIENT,
				false));
		userRoles.add(new UserRoleBox("Role expert", UserRoleDTO.ROLE_EXPERT,
				false));
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
		form.add(fullName, formData);
	}
}
