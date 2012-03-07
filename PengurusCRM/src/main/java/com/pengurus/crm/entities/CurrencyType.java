package com.pengurus.crm.entities;

import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public class CurrencyType {
    
    private Long id;
    private String currency;

    public CurrencyType() {
        super();
    }

    public CurrencyType(String currency) {
        super();
        this.currency = currency;
    }

    public CurrencyType(CurrencyTypeDTO currencyDTO) {
		if(currencyDTO != null){
			id = currencyDTO.getId();
			currency = currencyDTO.getCurrency();
		}
	}

	public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CurrencyTypeDTO toDTO(){
    	CurrencyTypeDTO ctDTO = new CurrencyTypeDTO();
    	ctDTO.setCurrency(this.currency);
    	ctDTO.setId(this.id);
		return ctDTO;
    }
    
}
