package com.pengurus.crm.client.panels.center.user.worker;

import java.util.Set;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.WorkerModel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkersListPanelByRoles extends WorkersListPanel {

	private Set<UserRoleDTO> roles;


	public WorkersListPanelByRoles(Listener<DomEvent> listenerChangeWorker,
			Listener<DomEvent> listenerCloseTab,Set<UserRoleDTO> roles) {
		this.listenerChangeWorker = listenerChangeWorker;
		this.listenerCloseTab = listenerCloseTab;
		this.roles = roles;
	}

	@Override
	protected ListStore<WorkerModel> getList() {
		final ListStore<WorkerModel> list = new ListStore<WorkerModel>();
		AsyncCallback<Set<UserDTO>> callback = new AsyncCallback<Set<UserDTO>>() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error");
				mb.show();
			}

			public void onSuccess(Set<UserDTO> result) {
				for (UserDTO q : result) {
					list.add(new WorkerModel((WorkerDTO)q));
				}
			}
		};
		UserServiceAsync service = (UserServiceAsync) GWT
				.create(UserService.class);
		service.getUsersByRoles(roles, callback);

		return list;
	}


}
