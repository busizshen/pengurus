package com.pengurus.crm.client.panels.center.task;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public class TasksListPanelViewByUser extends TasksListPanelView {

	UserDTO user;
	
	public TasksListPanelViewByUser(UserDTO user) {
		this.user = user;
		initPanel();
	}
	
	public TasksListPanelViewByUser(UserDTO user, int height, int width) {
		this.user = user;
		initPanel(height, width);
	}

	@Override
	protected ListStore<TaskModel> getList() {
		final ListStore<TaskModel> list = new ListStore<TaskModel>();
		AsyncCallback<Set<TaskDTO> > callback = new AsyncCallback<Set<TaskDTO> >() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error");
				mb.show();
			}

			public void onSuccess(Set<TaskDTO> result) {
				for(TaskDTO q: result){
					list.add(new TaskModel(q));
				}
			}
		};
		TaskServiceAsync service = (TaskServiceAsync) GWT
				.create(TaskService.class);
		service.getTaskByExpertId(user.getId(), callback);

		return list;
	}

	@Override
	protected String getName() {
		return user.getFullName() + " tasks list";
	}
	
}
