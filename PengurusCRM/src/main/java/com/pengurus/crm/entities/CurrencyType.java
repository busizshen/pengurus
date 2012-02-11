package com.pengurus.crm.entities;

public class CurrencyType {
    
//    private Long id;
    private String currency;

    public CurrencyType() {
        super();
    }

    public CurrencyType(String currency) {
        super();
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
/*    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  */  
}
