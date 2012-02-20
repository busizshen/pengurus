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

	private QuotesMenuPanel quotesMenu = new QuotesMenuPanel();
	private ProjectsMenuPanel projectsMenu = new ProjectsMenuPanel();
	private TasksMenuPanel tasksMenu = new TasksMenuPanel();
	private AdministrationMenuPanel administrationMenu = new AdministrationMenuPanel();
	private ContactsMenuPanel contactsMenu = new ContactsMenuPanel();

	public boolean add(TabMenuPanel tabMenuPanel) {
		if (tabMenuPanel.isEmpty()){
			return false;
		}
		return super.add(tabMenuPanel);
	}
	
	private MenuPanel() {
		setHeading("MENU");
		setBodyBorder(false);
		setLayout(new AccordionLayout());
		add(quotesMenu);
		add(projectsMenu);
		add(tasksMenu);
		add(administrationMenu);
		add(contactsMenu);
		setSize(200, 325);
	}

	public static ContentPanel getPanel() {
		return new MenuPanel();
	}
}