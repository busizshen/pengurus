package com.pengurus.crm.client.panel.header;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Window;

public class HeaderPanel extends ContentPanel {
	
	private HeaderPanel() {
		add(new Label("Pengurus CRM"));
		addToolbar();
	}

	private void addToolbar() {
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		addLogoutButton(toolBar);
		setBottomComponent(toolBar);
	}

	private void addLogoutButton(ToolBar toolBar) {
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				Window.Location.assign("/j_spring_security_logout");				
			}

		};
		Button logout = new Button("logout", listener);
		toolBar.add(logout);
	}
	
	public static ContentPanel getPanel() {
		return new HeaderPanel();
	}
	
}
