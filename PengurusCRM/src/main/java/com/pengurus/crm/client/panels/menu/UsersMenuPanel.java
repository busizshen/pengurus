package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.user.create.UserCreatePanel;

public class UsersMenuPanel extends TabMenuPanel {

	public UsersMenuPanel() {
		super("Users");
		addAllButton();
		addButtonClients();
		addButtonExecutives();
		addButtonExperts();
		addButtonVerificators();
		addButtonFreelancers();
		addCreateButton();
	}

	private void addAllButton() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button button = new Button("Create user");
			button.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
				}
			});
			add(button);
		}
	}

	private void addCreateButton() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button button = new Button("Create user");
			button.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					MainWindow.addCenterPanel(new UserCreatePanel());
				}
			});
			add(button);
		}
	}

	private void addButtonVerificators() {
		if (AuthorizationManager.canViewTranslators()) {
			Button b = new Button("Verificators");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// new VerificatorsListPanel();
				}
			});
			add(b);
		}
	}

	private void addButtonFreelancers() {
		if (AuthorizationManager.canViewTranslators()) {
			Button b = new Button("Freelancers");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// new FreelancersListPanel();
				}
			});
			add(b);
		}
	}

	private void addButtonExperts() {
		if (AuthorizationManager.canViewTranslators()) {
			Button b = new Button("Experts");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// new ExpertsListPanel();
				}
			});
			add(b);
		}
	}

	private void addButtonExecutives() {
		if (AuthorizationManager.canViewExecutives()) {
			Button b = new Button("Executives");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// new ExecutivesListPanel();
				}
			});
			add(b);
		}
	}

	private void addButtonClients() {
		if (AuthorizationManager.canViewClients()) {
			Button b = new Button("Clients");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// new ClientsListPanel();
				}
			});
			add(b);
		}

	}
}
