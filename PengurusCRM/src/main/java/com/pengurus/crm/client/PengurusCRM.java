package com.pengurus.crm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.client.service.CurrentSessionServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;

public class PengurusCRM implements EntryPoint {
	
	public MainWindow mainWindow;
	public AuthorizationManager authManager;
		
	public void onModuleLoad() {
		startWithCurrentUserLoaded();
	}
	
	private void startWithCurrentUserLoaded() {
		AsyncCallback<UserDTO> callback = new AsyncCallback<UserDTO>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(UserDTO result) {
				authManager = new AuthorizationManager();
				authManager.setCurrentUser(result);
				loadMainWindow();
			}
		};
		CurrentSessionServiceAsync service = (CurrentSessionServiceAsync) GWT
				.create(CurrentSessionService.class);
		service.getCurrentUser(callback);
	}

	private void loadMainWindow() {
		mainWindow = GWT.create(MainWindow.class);
		RootPanel.get().add(mainWindow);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

}
