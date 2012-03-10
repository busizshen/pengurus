package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.user.UserPanel.UserViewInfo;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelEdit;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEdit;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectPanelEdit extends ProjectPanel {

	WorkerPanelEdit supervisor;
	ClientPanelEdit client;
	public ProjectPanelEdit(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	@Override
	protected void addTranslatorsPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addTasksPanel() {
		// TODO Auto-generated method stub
		
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
		vp.setSpacing(20);
		
		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.setSpacing(5);
		Button b = new Button("Update");
		hp2.add(b);
		Button b1 = new Button("Cancel");
		hp2.add(b1);
		Button b2 = new Button("Delete");
		hp2.add(b2);
		vp.add(hp2);
		
		NumberField id = new NumberField();
		id.setValue(projectDTO.getId());
		id.setReadOnly(true);
		vp.add(id);
		
		Listener<DomEvent> listener = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				projectDTO.setSupervisor(supervisor.getChosenWorker());
				supervisor.refresh(projectDTO.getSupervisor());
			}
		};
		supervisor = new WorkerPanelEdit(projectDTO.getSupervisor(), listener);
		UserViewInfo supervisorPanel = supervisor.getInfoPanel();
		supervisorPanel.setHeading("Supervisor");
		supervisorPanel.expand();
		vp.add(supervisorPanel);
		
		if(AuthorizationManager.canViewWholeProject()){
			Listener<DomEvent> listener2 = new Listener<DomEvent>() {
				@Override
				public void handleEvent(DomEvent be) {
					projectDTO.setClient(client.getChosenClient());
					client.refresh(projectDTO.getClient());
				}
			};
			client = new ClientPanelEdit(projectDTO.getClient(),listener2);
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
