package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelChoose;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectPanelView extends ProjectPanel{

	public ProjectPanelView(ProjectDTO projectDTO) {
		super(projectDTO);
	}


	@Override
	protected void addDescriptionPanel(VerticalPanel vp) {
		descriptionPanel = new DescriptionPanelView(projectDTO.getDescription());
		descriptionPanel.setWidth(300);
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
	protected WorkerPanelChoose getTranslatorsPanel() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected WorkerPanelChoose getProjectMangaersPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
