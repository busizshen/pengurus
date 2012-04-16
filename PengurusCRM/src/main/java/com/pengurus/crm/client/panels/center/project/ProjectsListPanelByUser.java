package com.pengurus.crm.client.panels.center.project;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public abstract class ProjectsListPanelByUser extends ProjectsListPanel{

	UserDTO user;
	public ProjectsListPanelByUser(UserDTO user) {
		this.user = user;
		projectsList = new ModelList();
		add(projectsList);
	}

	@Override
	protected ListStore<ProjectModel> getList() {
		final ListStore<ProjectModel> list = new ListStore<ProjectModel>();
		AsyncCallback<Set<ProjectDTO> > callback = new AsyncCallback<Set<ProjectDTO> >() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Error service.getProjects()");
				mb.show();
			}

			public void onSuccess(Set<ProjectDTO> result) {
				for(ProjectDTO q: result){
					list.add(new ProjectModel(q));
				}
			}
		};

		changeService(callback);

		return list;
	}

	protected abstract void changeService(AsyncCallback<Set<ProjectDTO>> callback);

}
