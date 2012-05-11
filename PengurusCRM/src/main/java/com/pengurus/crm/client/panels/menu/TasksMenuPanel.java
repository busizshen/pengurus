package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.panels.center.task.TasksListPanelViewAll;
import com.pengurus.crm.client.panels.center.task.TasksListPanelViewByExpert;
import com.pengurus.crm.client.panels.center.task.TasksListPanelViewByReviewer;


public class TasksMenuPanel extends TabMenuPanel {
	private static i18nConstants myConstants=(i18nConstants)GWT.create(i18nConstants.class);

	public TasksMenuPanel() {
		super(myConstants.Tasks());
	    addButtonAll();
	    addButtonMineExpert();
	    addButtonMineReviewer();
	}
	
	public TabMenuPanel getPanel() {
		return new ProjectsMenuPanel();
	}

	private void addButtonMineExpert() {
		if(AuthorizationManager.canViewTasks()){ 
			Button b = new Button(myConstants.MineAsExpert());
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					TasksListPanelViewByExpert taskListPanel = new TasksListPanelViewByExpert(AuthorizationManager.getCurrentUser());
					taskListPanel.setAsMain();
				}
			});
		    add(b);
		}
	}
	
	private void addButtonMineReviewer() {
		if(AuthorizationManager.canViewTasks()){ 
			Button b = new Button(myConstants.MineAsReviewer());
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					TasksListPanelViewByReviewer taskListPanel = new TasksListPanelViewByReviewer(AuthorizationManager.getCurrentUser());
					taskListPanel.setAsMain();
				}
			});
		    add(b);
		}
	}

	private void addButtonAll() {
		if(AuthorizationManager.canViewAllTasks()){ 
			Button b = new Button(myConstants.All());
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					TasksListPanelViewAll taskListPanel = new TasksListPanelViewAll();
					taskListPanel.setAsMain();
				}
			});
		    add(b);
		}
	}

}
