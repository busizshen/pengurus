package com.pengurus.crm.client.panels.center.administration.translation;

import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.pengurus.crm.client.models.TranslationModel;

public class TranslationsListPanelView extends TranslationsListPanel {

	public TranslationsListPanelView(
			ListStore<TranslationModel> listTranslationModel) {
		super();
		list = listTranslationModel;
		modelList = new ModelList();
		add(modelList);
	}

	public TranslationsListPanelView(
			ListStore<TranslationModel> listTranslationModel, int height,
			int width) {
		super();
		list = listTranslationModel;
		modelList = new ModelList(height, width);
		add(modelList);
	}

	@Override
	protected void addButton(List<ColumnConfig> configs) {

	}

	@Override
	protected String getName() {
		return "Translations";
	}
	
	@Override
	protected void setStyle(ContentPanel cp) {
	//	setHeaderVisible(false);
		setAutoHeight(false);
		setAutoWidth(false);
	}

}
