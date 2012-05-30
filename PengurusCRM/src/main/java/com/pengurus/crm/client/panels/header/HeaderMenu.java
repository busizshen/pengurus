package com.pengurus.crm.client.panels.header;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.panels.center.ChangePasswordPanel;
import com.pengurus.crm.client.panels.center.user.create.UserPreviewPanel;

public class HeaderMenu extends ToolBar {

	private i18nConstants myConstants;

	public HeaderMenu() {
		myConstants = (i18nConstants) GWT.create(i18nConstants.class);
		setAlignment(HorizontalAlignment.RIGHT);
		addCurrentUserName();
		add(new SeparatorToolItem());
		addSettings();
		addLanguages();
		addLogout();
	}

	private void addCurrentUserName() {
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				new UserPreviewPanel(AuthorizationManager.getCurrentUser())
						.setAsMain();
			}

		};

		Button homeButton = new Button(AuthorizationManager.getCurrentUser()
				.getUsername(), listener);
		add(homeButton);
	}

	private void addLogout() {
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				Window.Location.assign("/j_spring_security_logout");
			}

		};
		Button logoutButton = new Button(myConstants.logout(), listener);
		add(logoutButton);
	}

	private void addSettings() {
		SplitButton settingsButton = new SplitButton(myConstants.settings());
		Menu settingsMenu = new Menu();
		addChangePassword(settingsMenu);
		settingsButton.setMenu(settingsMenu);
		add(settingsButton);
	}

	private void addLanguages() {
		SplitButton settingsButton = new SplitButton(myConstants.language());
		Menu settingsMenu = new Menu();
		String[] lang = { "en", "pl" };
		for (String s : lang) {
			addChangeLanguage(settingsMenu, s);
		}
		settingsButton.setMenu(settingsMenu);

		add(settingsButton);
	}

	private void addChangeLanguage(Menu settingsMenu, final String s) {
		MenuItem changePasswordItem = new MenuItem(s);
		changePasswordItem
				.addSelectionListener(new SelectionListener<MenuEvent>() {

					@Override
					public void componentSelected(MenuEvent ce) {
						Window.Location.assign( // or replace()
								Window.Location
										.createUrlBuilder()
										.setParameter(
												LocaleInfo
														.getLocaleQueryParam(),
												s).buildString());
					}
				});
		settingsMenu.add(changePasswordItem);
	}

	private void addChangePassword(Menu settingsMenu) {
		MenuItem changePasswordItem = new MenuItem(myConstants.changePassword());
		changePasswordItem
				.addSelectionListener(new SelectionListener<MenuEvent>() {

					@Override
					public void componentSelected(MenuEvent ce) {
						new ChangePasswordPanel().setAsMain();
					}
				});
		settingsMenu.add(changePasswordItem);
	}

}
