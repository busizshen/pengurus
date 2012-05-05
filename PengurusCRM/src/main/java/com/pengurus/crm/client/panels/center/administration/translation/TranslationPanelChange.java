package com.pengurus.crm.client.panels.center.administration.translation;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.ui.Label;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class TranslationPanelChange extends TranslationPanel {

	Label defaultPriceLabel;

	public TranslationPanelChange(TranslationDTO translationDTO,
			ListStore<TranslationModel> listTranslationModel) {
		addEditPanel(listTranslationModel);
		initTranslationPanel();

		if (translationDTO != null)
			setTranslationValues(translationDTO, 0, null);
	}

	public TranslationPanelChange(TranslationDTO translationDTO,
			ListStore<TranslationModel> listTranslationModel, Integer amount,
			PriceDTO price) {
		addEditPanel(listTranslationModel);
		initTranslationPanel();
		if (translationDTO != null)
			setTranslationValues(translationDTO, amount, price);
	}

	public TranslationPanelChange(
			ListStore<TranslationModel> listTranslationModel, Integer amount,
			PriceDTO price) {
		super();
		this.amount = amount;
		this.price = price;
		addEditPanel(listTranslationModel);
		initTranslationPanel();
	}

	@Override
	protected void initTranslationPanel() {
		setHeading("Translation");
		translationName = new Label();
		add(translationName);
		amountLabel = new Label();
		add(amountLabel);
		priceLabel = new Label();
		add(priceLabel);
		defaultPriceLabel = new Label();
		add(defaultPriceLabel);
		add(translations.getModelList());

	}

	private void addEditPanel(ListStore<TranslationModel> listTranslationModel) {
		Listener<DomEvent> listenerChosen = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				setTranslationValues(translations.getTranslation()
						.getTranslationDTO(), amount, price);
			}
		};
		translations = new TranslationsListPanelChoose(listTranslationModel,
				listenerChosen);

	}

	@Override
	protected void refresh() {
		if (translation != null) {
			translationName.setText(translation.toStringTranslation()
					+ " "
					+ translation.getTranslationDTO().getType()
							.getDescription());
			if (translation.getTranslationDTO().getType() != null)
				amountLabel.setText("Amount : "
						+ translation.toStringAmount(amount));
			if (price != null)
				priceLabel.setText("Price : " + price.toString());
			if (translation.getTranslationDTO().getDefaultPrice() != null)
				defaultPriceLabel.setText("Default Price : "
						+ translation.getTranslationDTO().getDefaultPrice()
								.toString());

		}
	}

}
