package com.pengurus.crm.client.panels.center.administration.translation;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.pengurus.crm.client.models.TranslationModel;

public class TranslationPanel extends FormPanel {

	TranslationModel translation ;
	TextField<String> from;
	TextField<String> to;
	TextField<String> type;
	TextField<Integer> price;
	private TranslationsListPanel translations;
	
	public TranslationPanel(ListStore<TranslationModel> listTranslationModel){
		this.setBorders(true);
		setHeading("Translation");
		from = new TextField<String>();
		from.setFieldLabel("From");
		from.setReadOnly(true);
		add(from);
		add(from);
		to = new TextField<String>();
		to.setFieldLabel("To");
		to.setReadOnly(true);
		add(to);
		type = new TextField<String>();
		type.setFieldLabel("Translation Type");
		type.setReadOnly(true);
		add(type);
		price = new TextField<Integer>();
		price.setFieldLabel("DefaultPrice");
		price.setReadOnly(true);
		add(price);
		Listener<DomEvent> listenerChosen = new Listener<DomEvent>(){
			@Override
			public void handleEvent(DomEvent be) {
				setTranslation(translations.getTranslation());
			}};
		translations = new TranslationsListPanel(listTranslationModel, listenerChosen);
		add(translations);
		
	}
	
	public void setTranslation(TranslationModel translation) {
		this.translation = translation;
		refresh();
	}
	
	private void refresh(){
		from.setValue(translation.getTranslationDTO().getFrom().getLanguage());
		to.setValue(translation.getTranslationDTO().getTo().getLanguage());
		type.setValue(translation.getTranslationDTO().getType().getDescription() + "  " + translation.getTranslationDTO().getType().getUnit());
		price.setValue(translation.getTranslationDTO().getDefaultPrice().getPrice());
	}

	public void setAllowBlank(boolean b) {
		from.setAllowBlank(b);
		to.setAllowBlank(b);
	}

	public TranslationModel getTranslation() {
		return translation;
	}

}
