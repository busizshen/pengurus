package com.pengurus.crm.client.panels.header;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Window;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.ChangePasswordPanel;

public class HeaderMenu extends ToolBar {
	
	public HeaderMenu() {
		setAlignment(HorizontalAlignment.RIGHT);
		addCurrentUserName();
		add(new SeparatorToolItem());
		addSettings();
		addLogout();
	}

	private void addCurrentUserName() {
		LabelToolItem usernameLabel = new LabelToolItem();
		usernameLabel.setLabel(AuthorizationManager.getCurrentUser()
				.getUsername());
		add(usernameLabel);
	}

	private void addLogout() {
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				Window.Location.assign("/j_spring_security_logout");				
			}

		};
		Button logoutButton = new Button("logout", listener);
		add(logoutButton);
	}
	
	private void addSettings() {
		SplitButton settingsButton = new SplitButton("settings");
		Menu settingsMenu = new Menu();
		addChangePassword(settingsMenu);
		settingsButton.setMenu(settingsMenu);
		add(settingsButton);
	}

	private void addChangePassword(Menu settingsMenu) {
		MenuItem changePasswordItem = new MenuItem("Change password");
		changePasswordItem
				.addSelectionListener(new SelectionListener<MenuEvent>() {
 
					@Override
					public void componentSelected(MenuEvent ce) {
						MainWindow.addWidgetCenterPanel(ChangePasswordPanel
								.getPanel());
					}
				});
		settingsMenu.add(changePasswordItem);
	}

}
