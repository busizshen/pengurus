package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;


@RemoteServiceRelativePath("administration.rpc")
public interface AdministrationService extends RemoteService {
    public Set<CurrencyTypeDTO> getCurrency();
    public CurrencyTypeDTO createCurrency(CurrencyTypeDTO currencyTypeDTO);
    public CurrencyTypeDTO deleteCurrency(CurrencyTypeDTO currencyTypeDTO);
    
    public Set<LanguageDTO> getLanguages();
    public LanguageDTO createLanguage(LanguageDTO languageDTO);
    public LanguageDTO deleteLanguage(LanguageDTO languageDTO);
    
    public Set<TranslationDTO> getTranslations();
}