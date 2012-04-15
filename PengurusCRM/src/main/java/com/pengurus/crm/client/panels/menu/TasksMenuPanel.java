package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.task.TasksListPanelAll;


public class TasksMenuPanel extends TabMenuPanel {

	public TasksMenuPanel() {
		super("Tasks");
	    addButtonAll();
	    addButtonMine();
	}
	
	public TabMenuPanel getPanel() {
		return new ProjectsMenuPanel();
	}

	private void addButtonMine() {
		if(AuthorizationManager.canViewTasks()){ 
			Button b = new Button("Mine");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					MessageBox mb = new MessageBox();
					mb.setMessage("Nie ma getByUserId w bazie danych");
					mb.show();
				}
			});
		    add(b);
		}
	}

	private void addButtonAll() {
		if(AuthorizationManager.canViewAllTasks()){ 
			Button b = new Button("All");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					TasksListPanelAll taskListPanel = new TasksListPanelAll();
					taskListPanel.setAsMain();
				}
			});
		    add(b);
		}
	}

}
