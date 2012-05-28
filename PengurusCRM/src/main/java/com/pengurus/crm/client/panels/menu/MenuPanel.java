package com.pengurus.crm.client.panels.menu;

/* 
 * Ext GWT 2.2.5 - Ext for GWT 
 * Copyright(c) 2007-2010, Ext JS, LLC. 
 * licensing@extjs.com 
 *  
 * http://extjs.com/license 
 */

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class MenuPanel extends ContentPanel {

	private QuotesMenuPanel quotesMenu;
	private ProjectsMenuPanel projectsMenu;
	private TasksMenuPanel tasksMenu;
	private AdministrationMenuPanel administrationMenu;
	private UsersMenuPanel usersMenu;

	public boolean add(TabMenuPanel tabMenuPanel) {
		if (tabMenuPanel.isEmpty()){
			return false;
		}
		return super.add(tabMenuPanel);
	}
	
	public MenuPanel() {
		quotesMenu = new QuotesMenuPanel();
		projectsMenu = new ProjectsMenuPanel();
		tasksMenu = new TasksMenuPanel();
		administrationMenu = new AdministrationMenuPanel();
		usersMenu = new UsersMenuPanel();
		setHeading("MENU");
		setBodyBorder(false);
		setLayout(new AccordionLayout());
		add(quotesMenu);
		add(projectsMenu);
		add(tasksMenu);
		add(usersMenu);
		add(administrationMenu);
		setSize(200, 325);
	}
}