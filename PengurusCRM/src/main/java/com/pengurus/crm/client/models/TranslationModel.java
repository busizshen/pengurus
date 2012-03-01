package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class TranslationModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2613102464810842219L;

	private TranslationDTO translationDTO;
	public TranslationModel(TranslationDTO c) {
		setTranslationDTO(c);
	}
	public void setTranslationDTO(TranslationDTO translationDTO) {
		this.translationDTO = translationDTO;
		if(translationDTO != null){
			if(translationDTO.getDefaultPrice() != null){
				set("defaultPrice", translationDTO.getDefaultPrice().getPrice());
				set("defaultPriceCurrency", translationDTO.getDefaultPrice().getCurrency().getCurrency());
			}
			if(translationDTO.getFrom() != null){
				set("from",translationDTO.getFrom().getLanguage());
			}
			if(translationDTO.getTo() != null){
				set("to",translationDTO.getTo().getLanguage());
			}
			if(translationDTO.getType() != null){
				set("type",translationDTO.getType().getDescription());
			}
		}
	}
	public TranslationDTO getTranslationDTO() {
		return translationDTO;
	}
}
