package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CurrencyTypeDTO implements IsSerializable {
//  private Long id;
    private String currency;

    public CurrencyTypeDTO() {
        super();
    }

    public CurrencyTypeDTO(String currency) {
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

