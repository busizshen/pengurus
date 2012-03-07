package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public interface AdministrationServiceAsync {

    public void getCurrency(AsyncCallback<Set<CurrencyTypeDTO>> callback);

    public void createCurrency(CurrencyTypeDTO currencyTypeDTO,
            AsyncCallback<CurrencyTypeDTO> callback);

    public void deleteCurrency(CurrencyTypeDTO currencyTypeDTO,
            AsyncCallback<CurrencyTypeDTO> callback);

    public void getLanguages(AsyncCallback<Set<LanguageDTO>> callback);

    public void createLanguage(LanguageDTO languageDTO,
            AsyncCallback<LanguageDTO> callback);

    public void deleteLanguage(LanguageDTO languageDTO,
            AsyncCallback<LanguageDTO> callback);

    public void getTranslations(AsyncCallback<Set<TranslationDTO>> callback);

}