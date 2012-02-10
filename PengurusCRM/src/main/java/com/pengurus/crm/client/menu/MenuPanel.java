package com.pengurus.crm.client.menu;

/* 
 * Ext GWT 2.2.5 - Ext for GWT 
 * Copyright(c) 2007-2010, Ext JS, LLC. 
 * licensing@extjs.com 
 *  
 * http://extjs.com/license 
 */   
  
import com.extjs.gxt.ui.client.data.BaseModelData;  
import com.extjs.gxt.ui.client.data.ModelData;  
import com.extjs.gxt.ui.client.data.ModelIconProvider;  
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;  
import com.extjs.gxt.ui.client.util.IconHelper;  
import com.extjs.gxt.ui.client.widget.ContentPanel;  
import com.extjs.gxt.ui.client.widget.LayoutContainer;  
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;  
import com.extjs.gxt.ui.client.widget.layout.FitLayout;  
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;  
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;  
import com.google.gwt.user.client.Element;  
import com.google.gwt.user.client.ui.AbstractImagePrototype;  
import com.pengurus.crm.client.MainWindow;
  
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