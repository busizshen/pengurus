package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public interface AdministrationServiceAsync {

	public void getCurrency(AsyncCallback<Set<CurrencyTypeDTO>> callback);
	public void getTranslations(AsyncCallback<Set<TranslationDTO>> callback);

}
