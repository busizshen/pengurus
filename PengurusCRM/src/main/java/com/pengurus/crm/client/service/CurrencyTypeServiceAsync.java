package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public interface CurrencyTypeServiceAsync {
    public void getCurrencyTypes(AsyncCallback<Set<CurrencyTypeDTO>> callback);

    public void createCurrencyType(CurrencyTypeDTO currencyTypeDTO,
            AsyncCallback<Void> callback);

    public void deleteCurrencyType(CurrencyTypeDTO currencyTypeDTO,
            AsyncCallback<Void> callback);

}
