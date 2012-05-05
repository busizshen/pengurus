package com.pengurus.crm.client.panels.center.administration.translation;

import com.google.gwt.user.client.ui.Label;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.shared.dto.PriceDTO;

public class TranslationPanelView extends TranslationPanel {


	public TranslationPanelView(TranslationModel translation, Integer amount,
			PriceDTO price) {
		super(translation);
		initTranslationPanel();
		if (translation != null)
			setTranslationValues(translation,amount,price);
	}

	public TranslationPanelView() {
		super();
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
	}

	@Override
	protected void refresh() {

		if (translation != null) {
			translationName.setText(translation.toStringTranslation()+ " " + translation.getTranslationDTO().getType().getDescription());
			if (translation.getTranslationDTO().getType() != null)
				if(amount != null)
					amountLabel.setText("Amount : " + translation.toStringAmount(amount));
				else amountLabel.setText("Amount : " + translation.toStringAmount(0));
			if (price  != null)
				priceLabel.setText("Price : " + price.toString());
		}

	}

}
