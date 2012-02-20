package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.UserCreatePanel;

public class UsersMenuPanel extends TabMenuPanel {

	public UsersMenuPanel() {
		super("Users");
		addCreateButton();
	}

	private void addCreateButton() {
		Button button = new Button("Create user");
		button.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				MainWindow.addWidgetCenterPanel(new UserCreatePanel());
				
			}
		});
		add(button);
	}

}
