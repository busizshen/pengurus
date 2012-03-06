package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

@RemoteServiceRelativePath("currencyType.rpc")
public interface CurrencyTypeService extends RemoteService {
    public Set<CurrencyTypeDTO> getCurrencyTypes();
    public void createCurrencyType(CurrencyTypeDTO currencyTypeDTO);
    public void deleteCurrencyType(CurrencyTypeDTO currencyTypeDTO);

}
