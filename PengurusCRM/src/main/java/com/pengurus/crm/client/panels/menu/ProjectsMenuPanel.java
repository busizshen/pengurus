package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.panels.center.project.ProjectsListPanelAll;
import com.pengurus.crm.client.panels.center.project.ProjectsListPanelByUser;
import com.pengurus.crm.client.panels.center.project.ProjectsListPanelByUserProjectManager;
import com.pengurus.crm.client.panels.center.project.ProjectsListPanelByUserSupervisor;

public class ProjectsMenuPanel extends TabMenuPanel {

	private static i18nConstants myConstants=(i18nConstants)GWT.create(i18nConstants.class);

	public ProjectsMenuPanel() {
		super(myConstants.Projects());
	    addButtonAll();
	    addButtonMineSupervisor();
	    addButtonMineProjectManager();
	}
	
	public TabMenuPanel getPanel() {
		return new ProjectsMenuPanel();
	}
	
	private void addButtonMineSupervisor() {
		if(AuthorizationManager.canViewAllProjects()){ 
			Button b = new Button(myConstants.MineAsSupervisor());
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					ProjectsListPanelByUser pp = new ProjectsListPanelByUserSupervisor(AuthorizationManager.getCurrentUser());
					pp.setAsMain();
				}
			});
		    add(b);
		}
	}

	private void addButtonMineProjectManager() {
		if(AuthorizationManager.canViewProjects()){ 
			Button b = new Button(myConstants.MineAsProjectManager());
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					ProjectsListPanelByUser pp = new ProjectsListPanelByUserProjectManager(AuthorizationManager.getCurrentUser());
					pp.setAsMain();
				}
			});
		    add(b);
		}
	}
	private void addButtonAll() {
		if(AuthorizationManager.canViewAllProjects()){ 
			Button b = new Button(myConstants.All());
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					ProjectsListPanelAll pp = new ProjectsListPanelAll();
					pp.setAsMain();
				}
			});
		    add(b);
		}
	}

}
