package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
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
		
	}

	@Override
	protected void addProjectMangaersPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addInfoPanel() {
		FormPanel simple = new FormPanel(); 
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		simple.setAutoHeight(true);
		simple.setAutoWidth(true);
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.setSpacing(5);
		Button b = new Button("Edit", new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
				ProjectPanelEdit projectPanel = new ProjectPanelEdit(projectDTO);
				projectPanel.setAsMain();
			}
			
		});
		hp2.add(b);

		vp.add(hp2);
		
		NumberField id = new NumberField();
		id.setValue(projectDTO.getId());
		id.setReadOnly(true);
		vp.add(id);
		
		supervisor = new WorkerPanelView(projectDTO.getSupervisor());
		UserViewInfo supervisorPanel = supervisor.getInfoPanel();
		supervisorPanel.setHeading("Supervisor");
		supervisorPanel.expand();
		vp.add(supervisorPanel);
		
		if(AuthorizationManager.canViewWholeProject()){
			client = new ClientPanelView(projectDTO.getClient());
			UserViewInfo clientPanel = client.getInfoPanel();
			clientPanel.setHeading("Client");
			clientPanel.expand();
			vp.add(clientPanel);
		}
		
		hp.add(vp);
		DescriptionPanelView descr = new DescriptionPanelView(projectDTO.getDescription());
		descr.setAutoWidth(true);
		hp.add(descr);
		simple.add(hp);
		add(simple);
	}

}
