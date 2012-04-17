package com.pengurus.crm.client.panels.center.project;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectsListPanelAll extends ProjectsListPanel{

	public ProjectsListPanelAll() {
		modelList = new ModelList();
		add(modelList);
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
		ProjectServiceAsync service = (ProjectServiceAsync) GWT
				.create(ProjectService.class);
		service.getProjects(callback);

		return list;
	}

}
