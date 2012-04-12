package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectPanelView extends ProjectPanel{

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
