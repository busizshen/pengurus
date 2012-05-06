package com.pengurus.crm.client.panels.center.administration.translation;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.ListPanel;

public abstract class TranslationsListPanel extends ListPanel<TranslationModel> {

	ListStore<TranslationModel> list;

	public TranslationsListPanel() {
		super(360);
	}
	
	public TranslationsListPanel(int height){
		super(height);
	}

	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfigMy();
		column = new ColumnConfigMy();
		column.setId("type");
		column.setHeader("Type");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("from");
		column.setHeader("From");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("to");
		column.setHeader("To");
		configs.add(column);

		addButton(configs);

		return configs;
	}

	protected abstract void addButton(List<ColumnConfig> configs);

	@Override
	protected ListStore<TranslationModel> getList() {
		return list;
	}

	@Override
	protected GridFilters getFilters() {
		return new GridFilters();
	}

}
