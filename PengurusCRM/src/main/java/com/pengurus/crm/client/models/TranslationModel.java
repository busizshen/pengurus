package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class TranslationModel extends BaseModel {
	private static final long serialVersionUID = -2613102464810842219L;

	private TranslationDTO translationDTO;

	public TranslationModel(TranslationDTO c) {
		setTranslationDTO(c);
	}

	public void setTranslationDTO(TranslationDTO translationDTO) {
		this.translationDTO = translationDTO;
		set("id", translationDTO.getId());
		if (translationDTO.getDefaultPrice() != null) {
			set("defaultPrice", translationDTO.getDefaultPrice().getPrice());
			if (translationDTO.getDefaultPrice().getCurrency() != null)
				set("defaultPriceCurrency", translationDTO.getDefaultPrice()
						.getCurrency().getCurrency());
		}
		if (translationDTO.getFrom() != null) {
			set("from", translationDTO.getFrom().getLanguage());
		}
		if (translationDTO.getTo() != null) {
			set("to", translationDTO.getTo().getLanguage());
		}
		if (translationDTO.getType() != null) {
			set("type", translationDTO.getType().getDescription());
		}
		set("name", toString());
	}

	public String getId() {
		return get("id");
	}
	
	public String getFrom() {
		return get("from");
	}

	public String getTo() {
		return get("to");
	}

	public String getType() {
		return get("type");
	}

	public Integer getDefaultPrice() {
		return get("defaultPrice");
	}

	public String getDefaultPriceCurrency() {
		return get("defaultPriceCurrency");
	}
	
	public String getName() {
		return get("name");
	}

	public TranslationDTO getTranslationDTO() {
		return translationDTO;
	}

	@Override
	public String toString() {
		return "" + getFrom() + " -> " + getTo() + " - " + getTo() + " ("
				+ getDefaultPrice() + " " + getDefaultPriceCurrency() + ")";
	}
}
