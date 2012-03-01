package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class AdministrationServiceImpl implements AdministrationService{

	CurrencyTypeDAO currencyDAO;
	TranslationDAO translationDAO;
	public TranslationDAO getTranslationDAO() {
		return translationDAO;
	}
	public void setTranslationDAO(TranslationDAO translationDAO) {
		this.translationDAO = translationDAO;
	}
	@Override
	public Set<CurrencyTypeDTO> getCurrency() {
		Set<CurrencyTypeDTO> currencyTypes = new HashSet<CurrencyTypeDTO>();
		List<CurrencyType> list = currencyDAO.loadAll();
		for(CurrencyType c : list)
			currencyTypes.add(c.toDTO());
		return currencyTypes;
	}
	public CurrencyTypeDAO getCurrencyDAO() {
		return currencyDAO;
	}
	public void setCurrencyDAO(CurrencyTypeDAO currencyDAO) {
		this.currencyDAO = currencyDAO;
	}
	@Override
	public Set<TranslationDTO> getTranslations() {
		Set<TranslationDTO> trnaslations = new HashSet<TranslationDTO>();
		List<Translation> list = translationDAO.loadAll();
		for(Translation c : list)
			trnaslations.add(c.toDTO());
		return trnaslations;
	}

}
