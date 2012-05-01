package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.exceptions.DependencyException;
import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.entities.TranslationType;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslationTypeDTO;

public class AdministrationServiceImpl implements AdministrationService {

	private CurrencyTypeDAO currencyTypeDAO;
	private TranslationDAO translationDAO;
	private LanguageDAO languageDAO;
	private TranslationTypeDAO translationTypeDAO;

	public TranslationTypeDAO getTranslationTypeDAO() {
		return translationTypeDAO;
	}

	public void setTranslationTypeDAO(TranslationTypeDAO translationTypeDAO) {
		this.translationTypeDAO = translationTypeDAO;
	}

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
		for (CurrencyType c : list)
			currencyTypes.add(c.toDTO());
		return currencyTypes;
	}

	@Override
	public CurrencyTypeDTO createCurrency(CurrencyTypeDTO currencyTypeDTO) {
		currencyTypeDTO.setId(currencyTypeDAO.create(new CurrencyType(
				currencyTypeDTO.getCurrency())));
		return currencyTypeDTO;
	}

	@Override
	public CurrencyTypeDTO deleteCurrency(CurrencyTypeDTO currencyTypeDTO)
			throws DependencyException {
		boolean result = currencyTypeDAO.delete(new CurrencyType(
				currencyTypeDTO));
		if (!result)
			throw new DependencyException();
		return currencyTypeDTO;
	}

	@Override
	public Set<LanguageDTO> getLanguages() {
		List<Language> list = languageDAO.loadAll();
		Set<LanguageDTO> languagesDTO = new HashSet<LanguageDTO>();
		for (Language language : list)
			languagesDTO.add(language.toDTO());
		return languagesDTO;
	}

	@Override
	public LanguageDTO createLanguage(LanguageDTO languageDTO) {
		languageDTO.setId(languageDAO.create(new Language(languageDTO
				.getLanguage())));
		return languageDTO;
	}

	@Override
	public LanguageDTO deleteLanguage(LanguageDTO languageDTO)
			throws DependencyException {
		boolean result = languageDAO.delete(new Language(languageDTO));
		if (!result)
			throw new DependencyException();
		return languageDTO;
	}

	@Override
	public Set<TranslationDTO> getTranslations() {
		Set<TranslationDTO> translations = new HashSet<TranslationDTO>();
		List<Translation> list = translationDAO.loadAll();
		for (Translation c : list)
			translations.add(c.toDTO());
		return translations;
	}

	@Override
	public TranslationDTO createTranslation(TranslationDTO translationDTO) {
		Translation translation = new Translation(translationDTO);
		translationDTO.setId(translationDAO.create(translation));
		translationDTO.getDefaultPrice().setId(
				translation.getDefaultPrice().getId());
		return translationDTO;
	}

	@Override
	public TranslationDTO deleteTranslation(TranslationDTO translationDTO)
			throws DependencyException {
		boolean result = translationDAO.delete(new Translation(translationDTO));
		if (!result)
			throw new DependencyException();
		return translationDTO;
	}

	@Override
	public Set<TranslationTypeDTO> getTranslationTypes() {
		Set<TranslationTypeDTO> translationTypes = new HashSet<TranslationTypeDTO>();
		List<TranslationType> list = translationTypeDAO.loadAll();
		for (TranslationType c : list)
			translationTypes.add(c.toDTO());
		return translationTypes;
	}

	@Override
	public TranslationTypeDTO createTranslationType(
			TranslationTypeDTO translationTypeDTO) {
		translationTypeDTO.setId(translationTypeDAO.create(new TranslationType(
				translationTypeDTO)));
		return translationTypeDTO;
	}

	@Override
	public TranslationTypeDTO deleteTranslationType(
			TranslationTypeDTO translationTypeDTO) throws DependencyException {
		boolean result = translationTypeDAO.delete(new TranslationType(
				translationTypeDTO));
		if (!result)
			throw new DependencyException();
		return translationTypeDTO;
	}

}