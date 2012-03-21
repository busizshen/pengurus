package com.pengurus.crm.client.panels.center.administration.translation;

import com.extjs.gxt.ui.client.store.ListStore;
import com.pengurus.crm.client.models.TranslationModel;

public class TranslationPanelCreate extends TranslationPanel {

	public TranslationPanelCreate(
			ListStore<TranslationModel> listTranslationModel) {
		super();
		addEditPanel(listTranslationModel);
	}

}
