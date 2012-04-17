package com.pengurus.crm.client.panels.center.administration.translation;

import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
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

	@Override
	protected void addButton(List<ColumnConfig> configs) {
		// tu wstawiaj guziki

	}

	@Override
	protected String getName() {
		return "Translations";
	}

}
