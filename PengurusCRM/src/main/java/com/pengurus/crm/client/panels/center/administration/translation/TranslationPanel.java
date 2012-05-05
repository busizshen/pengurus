package com.pengurus.crm.client.panels.center.administration.translation;

import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.Label;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.shared.dto.PriceDTO;

public abstract class TranslationPanel extends FieldSet {

	TranslationsListPanelChoose translations;
	TranslationModel translation;
	Label amountLabel;
	Label priceLabel;
	Label translationName;
	Integer amount;
	PriceDTO price;

	

	public TranslationPanel() {
		
	}

	public TranslationPanel(TranslationModel translation) {

	}

	protected abstract void initTranslationPanel();

	public void setTranslationValues(TranslationModel translation,Integer amount,
			PriceDTO price) {
		this.amount = amount;
		this.price = price;
		this.translation = translation;
		refresh();
	}

	protected abstract void refresh();
	
	public TranslationModel getTranslation() {
		return translation;
	}



}
