package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.TranslatorService;
import com.pengurus.crm.daos.TranslatorDAO;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class TranslatorServiceImpl implements TranslatorService {

	private TranslatorDAO translatorDAO;
	
	public TranslatorDAO getTranslatorDAO() {
		return translatorDAO;
	}
	
	public void setTranslatorDAO(TranslatorDAO translatorDAO) {
		this.translatorDAO = translatorDAO;
	}
	
	@Override
	public Set<TranslatorDTO> getTranslatorsByRoles(Set<UserRoleDTO> roles) {
		List<Translator> list = translatorDAO.loadAll();
		Set<TranslatorDTO> set = new HashSet<TranslatorDTO>();
		for (Translator u : list) {
			for (UserRoleDTO role : roles) {
				if (u.getAuthorities().contains(role)){
					set.add(u.toDTO());
					break;
				}
			}
		}
		return set;
	}

}
