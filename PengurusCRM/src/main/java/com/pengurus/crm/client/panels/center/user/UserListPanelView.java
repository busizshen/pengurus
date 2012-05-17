package com.pengurus.crm.client.panels.center.user;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.panels.center.ListPanel;

public class UserListPanelView extends ListPanel<UserModel> {

	ListStore<UserModel> list;
	String heading;
	public UserListPanelView(ListStore<UserModel> list, String heading){
		super(150);
		this.heading = heading;
		this.list = list;
		modelList = new ModelList(150, 300);

	}


	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs   = new ArrayList<ColumnConfig>();  
		ColumnConfig column = new ColumnConfig();
	    column.setId("fullName");
	    column.setHeader(myConstants.FullName());
	    configs.add(column);
	    return configs;
	}

	@Override
	protected String getName() {
		return heading;
	}

	@Override
	protected ListStore<UserModel> getList() {
		return list;
	}

	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();  
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		setWidth(300);
		setWidth(200);
	}

}
