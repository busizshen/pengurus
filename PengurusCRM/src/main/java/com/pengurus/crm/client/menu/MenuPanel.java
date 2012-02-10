package com.pengurus.crm.client.menu;

/* 
 * Ext GWT 2.2.5 - Ext for GWT 
 * Copyright(c) 2007-2010, Ext JS, LLC. 
 * licensing@extjs.com 
 *  
 * http://extjs.com/license 
 */   
  
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
  
public class MenuPanel {  
	
  private QuotesMenuPanel quotesMenu = new QuotesMenuPanel();
  private ProjectsMenuPanel projectsMenu = new ProjectsMenuPanel();
  private TasksMenuPanel tasksMenu = new TasksMenuPanel();
  private AdministrationMenuPanel administrationMenu = new AdministrationMenuPanel();
  private ContactsMenuPanel contactsMenu = new ContactsMenuPanel();

  public ContentPanel getPanel() {
	ContentPanel panel = new ContentPanel();  
    panel.setHeading("MENU");  
    panel.setBodyBorder(false);  
    panel.setLayout(new AccordionLayout());    
  
    panel.add(quotesMenu.getPanel()); 
    panel.add(projectsMenu.getPanel());
    panel.add(tasksMenu.getPanel());
    panel.add(administrationMenu.getPanel());
    panel.add(contactsMenu.getPanel());
    panel.setSize(200, 325);
    return panel;
	
}  
  
}  