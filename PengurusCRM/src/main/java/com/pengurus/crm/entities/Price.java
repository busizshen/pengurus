package com.pengurus.crm.entities;

import com.pengurus.crm.shared.dto.PriceDTO;

public class Price {

    private Long id;
    private Integer price;
    private CurrencyType currency;
    
    public Price() {
        super();
    }
    
    public Price(Integer price, CurrencyType currency) {
        super();
        this.price = price;
        this.currency = currency;
    }

    public Price(PriceDTO priceDTO) {
		super();
		if(priceDTO != null){
		    this.id = priceDTO.getId();
			this.price = priceDTO.getPrice();
			if(priceDTO.getCurrency() != null)
				this.currency = new CurrencyType(priceDTO.getCurrency());
		}
	}

	public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public PriceDTO toDTO() {
		PriceDTO pDTO = new PriceDTO();
		if(this.currency != null)
			pDTO.setCurrency(this.currency.toDTO());
		pDTO.setId(this.id);
		pDTO.setPrice(this.price);
		return pDTO;
	}

    
    
}
