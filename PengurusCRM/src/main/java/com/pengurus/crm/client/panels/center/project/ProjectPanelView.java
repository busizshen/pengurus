package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.user.UserPanel.UserViewInfo;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectPanelView extends ProjectPanel{

	WorkerPanelView supervisor;
	ClientPanelView client;
	public ProjectPanelView(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	@Override
	protected void addTranslatorsPanel() {
		// there will be nothing 
	}

	@Override
	protected void addProjectMangaersPanel() {
		// there will be nothing 
		
	}

	@Override
	protected void addSupervisorPanel(VerticalPanel vp) {
		supervisor = new WorkerPanelView(projectDTO.getSupervisor());
		UserViewInfo supervisorPanel = supervisor.getInfoPanel();
		supervisorPanel.setHeading("Supervisor");
		supervisorPanel.expand();
		supervisorPanel.setCollapsible(false);
		vp.add(supervisorPanel);
	}

	@Override
	protected void addClientPanel(VerticalPanel vp) {
		if(AuthorizationManager.canViewWholeProject()){
			client = new ClientPanelView(projectDTO.getClient());
			UserViewInfo clientPanel = client.getInfoPanel();
			clientPanel.setHeading("Client");
			clientPanel.expand();
			clientPanel.setCollapsible(false);
			vp.add(clientPanel);
		}
	}

	@Override
	protected void addDescriptionPanel(HorizontalPanel hp) {
		descriptionPanel = new DescriptionPanelView(projectDTO.getDescription());
		descriptionPanel.setWidth(300);
		hp.add(descriptionPanel);
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

	

}
