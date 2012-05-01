package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.service.exceptions.DependencyException;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslationTypeDTO;


@RemoteServiceRelativePath("administration.rpc")
public interface AdministrationService extends RemoteService {
	public Set<CurrencyTypeDTO> getCurrency();
	public CurrencyTypeDTO createCurrency(CurrencyTypeDTO currencyTypeDTO);
	public CurrencyTypeDTO deleteCurrency(CurrencyTypeDTO currencyTypeDTO) throws DependencyException;
    
	public Set<LanguageDTO> getLanguages();
	public LanguageDTO createLanguage(LanguageDTO languageDTO);
	public LanguageDTO deleteLanguage(LanguageDTO languageDTO) throws DependencyException;
    
	public Set<TranslationDTO> getTranslations();
	public TranslationDTO createTranslation(TranslationDTO TranslationDTO);
	public TranslationDTO deleteTranslation(TranslationDTO TranslationDTO) throws DependencyException;
    
	public Set<TranslationTypeDTO> getTranslationTypes();
	public TranslationTypeDTO createTranslationType(TranslationTypeDTO translationTypeDTO);
	public TranslationTypeDTO deleteTranslationType(TranslationTypeDTO translationTypeDTO) throws DependencyException;
}