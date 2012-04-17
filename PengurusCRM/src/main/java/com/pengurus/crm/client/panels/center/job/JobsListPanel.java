package com.pengurus.crm.client.panels.center.job;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.models.JobModel;
import com.pengurus.crm.client.panels.center.ListPanel;

public abstract class JobsListPanel extends ListPanel<JobModel> {

	protected ListStore<JobModel> jobs = new ListStore<JobModel>();

	public JobsListPanel(){
		super();
	}

	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfigMy();
		column.setId("id");
		column.setHeader("Id");
		configs.add(column);

/*		column = new ColumnConfigMy();
		column.setId("status");
		column.setHeader("Status");
		configs.add(column);*/

		/*column = new ColumnConfigMy();
		column.setId("deadline");
		column.setHeader("Deadline");
		column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
		configs.add(column);
*/
		column = new ColumnConfigMy();
		column.setId("translationFrom");
		column.setHeader("Translation From");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationTo");
		column.setHeader("Translation To");
		configs.add(column);

/*		column = new ColumnConfigMy();
		column.setId("priceNumber");
		column.setHeader("Price");
		configs.add(column);*/

		
		  column = new ColumnConfigMy(); 
		  column.setId("preview");
		  column.setHeader("Preview"); 
		  column.setRenderer(getButtonRenderer());
		  configs.add(column);
		 
		return configs;
	}
	
	protected abstract GridCellRenderer<JobModel> getButtonRenderer();

	@Override
	protected String getName() {
		return "Job List";
	}

	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		cp.setCollapsible(false);
		cp.setAnimCollapse(false);
		cp.setHeaderVisible(false);
		cp.setFrame(false);
		cp.setBorders(true);
	}

	@Override
	protected ListStore<JobModel> getList() {
		return jobs;
	}

	public abstract Widget getPanel();

}