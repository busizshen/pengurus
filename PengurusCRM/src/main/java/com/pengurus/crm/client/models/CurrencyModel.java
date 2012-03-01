package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public class CurrencyModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8119484832572859046L;

	private CurrencyTypeDTO currencyDTO;
	
	public CurrencyModel(CurrencyTypeDTO currrencyDTO){
		this.setCurrencyDTO(currrencyDTO);
	}

	public CurrencyTypeDTO getCurrencyDTO() {
		return currencyDTO;
	}

	public void setCurrencyDTO(CurrencyTypeDTO currencyDTO) {
		this.currencyDTO = currencyDTO;
		if(currencyDTO != null)
			set("currency", this.currencyDTO.getCurrency());
	}
	
	public String getCurrency(){
		return get("currency");
	}
	
}
