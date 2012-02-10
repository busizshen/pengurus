package com.pengurus.crm.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;  
import com.extjs.gxt.ui.client.widget.LayoutContainer;  
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;  
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.menu.MenuPanel;

public class MainWindow extends LayoutContainer {
	private static ContentPanel menuPanel = new MenuPanel().getPanel();
	private static ContentPanel northPanel = new NorthPanel().getPanel();
	private static ContentPanel centerPanel = new ContentPanel();
	protected void onRender(Element target, int index) {  
	    super.onRender(target, index);  
	    final BorderLayout layout = new BorderLayout();  
	    setLayout(layout);  
	    setStyleAttribute("padding", "10px");  
	    
	    ContentPanel center = new ContentPanel();  
	    center.setHeading("BorderLayout Example");  
	    center.setScrollMode(Scroll.AUTOX);  
	  
	    ContentPanel east = new ContentPanel();  
	    ContentPanel south = new ContentPanel();  
	  
	    BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 100);  
	    northData.setCollapsible(true);  
	    northData.setFloatable(true);  
	    northData.setHideCollapseTool(true);  
	    northData.setSplit(true);  
	    northData.setMargins(new Margins(0, 0, 5, 0));  
	  
	    BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);  
	    westData.setSplit(true);  
	    westData.setCollapsible(true);  
	    westData.setMargins(new Margins(0,5,0,0));  
	  
	    BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);  
	    centerData.setMargins(new Margins(0));
	  
	    BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 150);  
	    eastData.setSplit(true);  
	    eastData.setCollapsible(true);  
	    eastData.setMargins(new Margins(0,0,0,5));  
	  
	    BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);  
	    southData.setSplit(true);  
	    southData.setCollapsible(true);  
	    southData.setFloatable(true);  
	    southData.setMargins(new Margins(5, 0, 0, 0));  
	  
	    add(northPanel, northData);  
	    add(menuPanel, westData);  
	    add(centerPanel, centerData);  
	    add(east, eastData);  
	    add(south, southData); 
	  }
	    
	   
	public static ContentPanel getMenuPanel() {
		return menuPanel;
	}
	public static void setMenuPanel(ContentPanel menuPanel) {
		MainWindow.menuPanel = menuPanel;
		MainWindow.menuPanel.layout();
	}
	public static ContentPanel getNorthPanel() {
		return northPanel;
	}
	public static void setNorthPanel(ContentPanel northPanel) {
		MainWindow.northPanel = northPanel;
		MainWindow.northPanel.layout();
	}
	public static ContentPanel getCenterPanel() {
		return centerPanel;
	}
	public static void addWidgetCenterPanel(Widget cp) {
		MainWindow.centerPanel.removeAll();
		MainWindow.centerPanel.add(cp);
		MainWindow.centerPanel.setAutoHeight(true);
		MainWindow.centerPanel.layout();
	}

}
