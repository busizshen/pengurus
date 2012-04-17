package com.pengurus.crm.client.panels.center.administration.translation;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.pengurus.crm.client.models.TranslationModel;

public class TranslationPanel extends FormPanel {

	TranslationsListPanelChoose translations;
	TranslationModel translation;
	TextField<String> from;
	TextField<String> to;
	TextField<String> type;
	TextField<Integer> price;
	

	public TranslationPanel() {
		initTranslationPanel();
	}

	public TranslationPanel(TranslationModel translation) {
		initTranslationPanel();
		if (translation != null)
			setTranslation(translation);
	}

	private void initTranslationPanel() {
		setHeading("Translation");
		from = new TextField<String>();
		from.setFieldLabel("From");
		from.setReadOnly(true);
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

	}

	public void setTranslation(TranslationModel translation) {
		this.translation = translation;
		refresh();
	}

	private void refresh() {
		if (translation != null) {
			if (translation.getTranslationDTO().getFrom() != null)
				from.setValue(translation.getTranslationDTO().getFrom()
						.getLanguage());

			if (translation.getTranslationDTO().getTo() != null)
				to.setValue(translation.getTranslationDTO().getTo()
						.getLanguage());
			if (translation.getTranslationDTO().getType() != null)
				type.setValue(translation.getTranslationDTO().getType()
						.getDescription()
						+ "  "
						+ translation.getTranslationDTO().getType().getUnit());

			if (translation.getTranslationDTO().getDefaultPrice() != null)
				price.setValue(translation.getTranslationDTO()
						.getDefaultPrice().getPrice());
		}
	}

	public void setAllowBlank(boolean b) {
		from.setAllowBlank(b);
		to.setAllowBlank(b);
	}

	public TranslationModel getTranslation() {
		return translation;
	}

	public void addEditPanel(ListStore<TranslationModel> listTranslationModel) {
		Listener<DomEvent> listenerChosen = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				setTranslation(translations.getTranslation());
			}
		};
		translations = new TranslationsListPanelChoose(listTranslationModel,
				listenerChosen);
		add(translations);

	}


}
