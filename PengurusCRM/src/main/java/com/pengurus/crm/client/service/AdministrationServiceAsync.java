package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslationTypeDTO;

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

    public void createTranslation(TranslationDTO translationDTO,
            AsyncCallback<TranslationDTO> callback);

    public void deleteTranslation(TranslationDTO translationDTO,
            AsyncCallback<TranslationDTO> callback);

    public void getTranslationTypes(
            AsyncCallback<Set<TranslationTypeDTO>> callback);

    public void createTranslationType(TranslationTypeDTO translationTypeDTO,
            AsyncCallback<TranslationTypeDTO> callback);

    public void deleteTranslationType(TranslationTypeDTO translationTypeDTO,
            AsyncCallback<TranslationTypeDTO> callback);
}