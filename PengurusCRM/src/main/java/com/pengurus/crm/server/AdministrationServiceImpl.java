package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class AdministrationServiceImpl implements AdministrationService{

    private CurrencyTypeDAO currencyTypeDAO;
    private TranslationDAO translationDAO;
    private LanguageDAO languageDAO;
    
    public CurrencyTypeDAO getCurrencyTypeDAO() {
        return currencyTypeDAO;
    }

    public void setCurrencyTypeDAO(CurrencyTypeDAO currencyTypeDAO) {
        this.currencyTypeDAO = currencyTypeDAO;
    }

    public TranslationDAO getTranslationDAO() {
        return translationDAO;
    }

    public void setTranslationDAO(TranslationDAO translationDAO) {
        this.translationDAO = translationDAO;
    }

    public LanguageDAO getLanguageDAO() {
        return languageDAO;
    }

    public void setLanguageDAO(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    @Override
    public Set<CurrencyTypeDTO> getCurrency() {
        Set<CurrencyTypeDTO> currencyTypes = new HashSet<CurrencyTypeDTO>();
        List<CurrencyType> list = currencyTypeDAO.loadAll();
        for(CurrencyType c : list)
            currencyTypes.add(c.toDTO());
        return currencyTypes;
    }
    
    @Override
    public CurrencyTypeDTO createCurrency(CurrencyTypeDTO currencyTypeDTO) {
        currencyTypeDAO.create(new CurrencyType(currencyTypeDTO.getCurrency()));
        return currencyTypeDTO;
    }

    @Override
    public CurrencyTypeDTO deleteCurrency(CurrencyTypeDTO currencyTypeDTO) {
        currencyTypeDAO.delete(new CurrencyType(currencyTypeDTO));
        return currencyTypeDTO;
    }

    @Override
    public Set<TranslationDTO> getTranslations() {
        Set<TranslationDTO> trnaslations = new HashSet<TranslationDTO>();
        List<Translation> list = translationDAO.loadAll();
        for(Translation c : list)
            trnaslations.add(c.toDTO());
        return trnaslations;
    }
    
    @Override
    public Set<LanguageDTO> getLanguages() {
        List<Language> list = languageDAO.loadAll();
        Set<LanguageDTO> languagesDTO = new HashSet<LanguageDTO>();
        for(Language language : list)
            languagesDTO.add(language.toDTO());
        return languagesDTO;
    }

    @Override
    public LanguageDTO createLanguage(LanguageDTO languageDTO) {
        languageDAO.create(new Language(languageDTO.getLanguage()));
        return languageDTO;
    }

    @Override
    public LanguageDTO deleteLanguage(LanguageDTO languageDTO) {
        languageDAO.delete(new Language(languageDTO));
        return languageDTO;
    }


}