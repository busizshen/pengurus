package com.pengurus.crm.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.panels.header.HeaderPanel;
import com.pengurus.crm.client.panels.menu.MenuPanel;

public class MainWindow extends LayoutContainer {
	private static ContentPanel menuPanel;
	private static ContentPanel northPanel;
	private static ContentPanel centerPanel;

	public MainWindow() {
		menuPanel = new MenuPanel();
		northPanel = new HeaderPanel();
		centerPanel = new ContentPanel();
		setLayoutSettings();
		addHeaderPanel();
		addMenuPanel();
		addCenterPanelt();
	}

	private void setLayoutSettings() {
		final BorderLayout layout = new BorderLayout();
		setLayout(layout);
		setStyleAttribute("padding", "10px");
	}

	private void addCenterPanelt() {
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(0));
		centerPanel.setHeaderVisible(false);
		add(centerPanel, centerData);
	}

	private void addMenuPanel() {
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0, 5, 0, 0));
		add(menuPanel, westData);
	}

	private void addHeaderPanel() {
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				147);
		northData.setCollapsible(true);
		northData.setFloatable(true);
		northData.setHideCollapseTool(true);
		northData.setSplit(true);
		northData.setMargins(new Margins(0, 0, 5, 0));
		add(northPanel, northData);
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

	public static void addCenterPanel(Widget centerPanel) {
		MainWindow.centerPanel.removeAll();
		MainWindow.centerPanel.add(centerPanel);
		MainWindow.centerPanel.setAutoHeight(true);
		MainWindow.centerPanel.layout();
	}

}
