package com.pengurus.crm.entities;

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

    
    
}
