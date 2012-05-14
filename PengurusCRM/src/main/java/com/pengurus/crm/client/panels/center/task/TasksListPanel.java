package com.pengurus.crm.client.panels.center.task;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.shared.dto.TaskDTO;

public abstract class TasksListPanel extends ListPanel<TaskModel> {

	public TasksListPanel(){
		super(350);
		setHeading(myConstants.Tasks());
	}
	
	public TasksListPanel(int height){
		super(height);
		setHeading(myConstants.Tasks());
	}
	
	protected ListStore<TaskModel> tasks = new ListStore<TaskModel>();
	
	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfigMy column = new ColumnConfigMy();
		column.setId("id");
		column.setHeader(myConstants.Id());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("status");
		column.setHeader(myConstants.Status());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("expert");
		column.setHeader(myConstants.Expert());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationFrom");
		column.setHeader(myConstants.From());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationTo");
		column.setHeader(myConstants.To());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("deadline");
		column.setHeader(myConstants.Deadline());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("description");
		column.setHeader(myConstants.Description());
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("preview");
		column.setHeader(myConstants.previewButton());
		column.setRenderer(getButtonRenderer());
		configs.add(column);

		return configs;
	}
	

	protected abstract GridCellRenderer<TaskModel> getButtonRenderer();


	@Override
	protected String getName() {
		return myMessages.ListPanel(myConstants.Tasks());
	}

	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		expand();
	}
	
	protected void refreshList(TaskDTO taskDTO){
		modelList.getGrid().stopEditing();
		modelList.getStore().add(
				new TaskModel(taskDTO));
		modelList.getGrid().startEditing(0, 0);
	}

}
