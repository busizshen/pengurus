package com.pengurus.crm.client.panel.menu;

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

	private QuotesMenuPanel quotesMenu = new QuotesMenuPanel();
	private ProjectsMenuPanel projectsMenu = new ProjectsMenuPanel();
	private TasksMenuPanel tasksMenu = new TasksMenuPanel();
	private AdministrationMenuPanel administrationMenu = new AdministrationMenuPanel();
	private ContactsMenuPanel contactsMenu = new ContactsMenuPanel();

	private MenuPanel() {
		setHeading("MENU");
		setBodyBorder(false);
		setLayout(new AccordionLayout());
		add(quotesMenu.getPanel());
		add(projectsMenu.getPanel());
		add(tasksMenu.getPanel());
		add(administrationMenu.getPanel());
		add(contactsMenu.getPanel());
		setSize(200, 325);
	}

	public static ContentPanel getPanel() {
		return new MenuPanel();
	}
}