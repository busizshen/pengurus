package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.user.UserListPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public class ProjectPanelView extends ProjectPanel{

	public ProjectPanelView(ProjectDTO projectDTO) {
		super(projectDTO);
	}


	@Override
	protected void addDescriptionPanel(HorizontalPanel vp) {
		descriptionPanel = new DescriptionPanelView(projectDTO.getDescription(),50,450);
		vp.add(descriptionPanel);
	}

	@Override
	protected void addButtonPanel(HorizontalPanel hp2) {
		Button b = new Button("Edit", new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
				ProjectPanelEdit projectPanel = new ProjectPanelEdit(projectDTO);
				projectPanel.setAsMain();
			}
			
		});
		hp2.add(b);
	}


	@Override
	protected void getTranslatorsPanel(HorizontalPanel hp3) {
		ListStore<UserModel> list= new ListStore<UserModel>();
		for(UserDTO u : projectDTO.getExperts())
			list.add(new UserModel(u));	
		UserListPanelView translatorsPanel = new UserListPanelView(list, "Experts");
		hp3.add(translatorsPanel.getModelList());
	}


	@Override
	protected void getProjectMangaersPanel(HorizontalPanel hp3) {
		ListStore<UserModel> list= new ListStore<UserModel>();
		for(UserDTO u : projectDTO.getProjectManagers())
			list.add(new UserModel(u));	
		UserListPanelView projectManagers = new UserListPanelView(list,"Project Managers");
		hp3.add(projectManagers.getModelList());
	}

	

}
