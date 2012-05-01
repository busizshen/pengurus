package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.TranslationTypeDTO;

public class TranslationTypeModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4343498713805951742L;
	private TranslationTypeDTO translationTypeDTO;

	public TranslationTypeModel(TranslationTypeDTO translationTypeDTO) {
		this.setTranslationTypeDTO(translationTypeDTO);
	}

	public String getType() {
		return get("type");
	}
	
	public String getUnit() {
		return get("unit");
	}

	public void setTranslationTypeDTO(TranslationTypeDTO translationTypeDTO) {
		this.translationTypeDTO = translationTypeDTO;
		if (translationTypeDTO != null){
			set("type", translationTypeDTO.getDescription());
			set("unit", translationTypeDTO.getUnit());
		}
		set("name", toString());
	}

	public TranslationTypeDTO getTranslationTypeDTO() {
		return this.translationTypeDTO;
	}
	
	@Override
	public String toString() {
		return getUnit() + " - " + getType();
	}
	
}
