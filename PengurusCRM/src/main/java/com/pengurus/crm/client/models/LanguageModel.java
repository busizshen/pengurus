package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.LanguageDTO;

public class LanguageModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1960371202794377214L;

	private LanguageDTO languageDTO;

	public LanguageModel(LanguageDTO languageDTO) {
		this.setLanguageDTO(languageDTO);
	}

	public String getLang() {
		return get("lang");
	}

	public void setLanguageDTO(LanguageDTO languageDTO) {
		this.languageDTO = languageDTO;
		if (languageDTO != null)
			set("lang", languageDTO.getLanguage());
	}

	public LanguageDTO getLanguageDTO() {
		return this.languageDTO;
	}

}
