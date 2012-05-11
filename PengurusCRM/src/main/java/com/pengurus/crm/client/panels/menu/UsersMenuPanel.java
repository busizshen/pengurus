package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.panels.center.user.UserListPanel;
import com.pengurus.crm.client.panels.center.user.create.UserCreatePanel;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class UsersMenuPanel extends TabMenuPanel {
	private static i18nConstants myConstants=(i18nConstants)GWT.create(i18nConstants.class);

	public UsersMenuPanel() {
		super(myConstants.Users());
		addAllButton();
		addClientsButton();
		addExecutivesButton();
		addExpertsButton();
		addVerificatorsButton();
		addFreelancersButton();
		addCreateButton();
	}

	private void addAllButton() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button button = new Button(myConstants.AllUsers());
			button.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					UserListPanel userListPanel = UserListPanel.getIntance();
					userListPanel.selectAll();
					userListPanel.refreshList();
					userListPanel.setAsMain();
				}
			});
			add(button);
		}
	}

	private void addVerificatorsButton() {
		if (AuthorizationManager.canViewTranslators()) {
			Button b = new Button(myConstants.Verificators());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					UserListPanel userListPanel = UserListPanel.getIntance();
					userListPanel.selectRole(UserRoleDTO.ROLE_VERIFICATOR);
					userListPanel.refreshList();
					userListPanel.setAsMain();
				}
			});
			add(b);
		}
	}

	private void addFreelancersButton() {
		if (AuthorizationManager.canViewTranslators()) {
			Button b = new Button(myConstants.Freelancers());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					UserListPanel userListPanel = UserListPanel.getIntance();
					userListPanel.selectRole(UserRoleDTO.ROLE_FREELANCER);
					userListPanel.refreshList();
					userListPanel.setAsMain();
				}
			});
			add(b);
		}
	}

	private void addExpertsButton() {
		if (AuthorizationManager.canViewTranslators()) {
			Button b = new Button(myConstants.Experts());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					UserListPanel userListPanel = UserListPanel.getIntance();
					userListPanel.selectRole(UserRoleDTO.ROLE_EXPERT);
					userListPanel.refreshList();
					userListPanel.setAsMain();
				}
			});
			add(b);
		}
	}

	private void addExecutivesButton() {
		if (AuthorizationManager.canViewExecutives()) {
			Button b = new Button(myConstants.Executives());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					UserListPanel userListPanel = UserListPanel.getIntance();
					userListPanel.selectRole(UserRoleDTO.ROLE_EXECUTIVE);
					userListPanel.refreshList();
					userListPanel.setAsMain();
				}
			});
			add(b);
		}
	}

	private void addClientsButton() {
		if (AuthorizationManager.canViewClients()) {
			Button b = new Button(myConstants.Clients());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					UserListPanel userListPanel = UserListPanel.getIntance();
					userListPanel.selectRole(UserRoleDTO.ROLE_CLIENT);
					userListPanel.refreshList();
					userListPanel.setAsMain();
				}
			});
			add(b);
		}
	}

	private void addCreateButton() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button button = new Button(myConstants.CreateUser());
			button.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					new UserCreatePanel().setAsMain();
				}
			});
			add(button);
		}
	}
}
