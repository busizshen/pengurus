package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PriceDTO implements IsSerializable {
	 	private Long id;
	    private Integer price;
	    private CurrencyTypeDTO currency;
	    
	    public PriceDTO() {
	        super();
	    }
	    
	    public PriceDTO(Integer price, CurrencyTypeDTO currency) {
	        super();
	        this.price = price;
	        this.currency = currency;
	    }

	    public Integer getPrice() {
	        return price;
	    }

	    public void setPrice(Integer price) {
	        this.price = price;
	    }

	    public CurrencyTypeDTO getCurrency() {
	        return currency;
	    }

	    public void setCurrency(CurrencyTypeDTO currency) {
	        this.currency = currency;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

}
