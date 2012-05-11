package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.panels.center.administration.currency.CurrencyTypePanel;
import com.pengurus.crm.client.panels.center.administration.language.LanguagePanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelAdmin;
import com.pengurus.crm.client.panels.center.administration.translationtype.TranslationTypePanel;


public class AdministrationMenuPanel extends TabMenuPanel {
	
	private static i18nConstants myConstants=(i18nConstants)GWT.create(i18nConstants.class);

	public AdministrationMenuPanel() {
		super((myConstants.Administration()));
	    addButtonTranslations();
	    addButtonLanguages();
	    addButtonTranslationTypes();
	    addButtonCurrency();
	}
	
	private void addButtonTranslations() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button(myConstants.Translations());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
				    new TranslationPanelAdmin().setAsMain();
				}
			});
			add(b);
		}
	}
	
	private void addButtonLanguages() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button(myConstants.Languages());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
				    new LanguagePanel().setAsMain();
				}
			});
			add(b);
		}
	}
	
	private void addButtonTranslationTypes() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button(myConstants.TranslationTypes());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
				    new TranslationTypePanel().setAsMain();
				}
			});
			add(b);
		}
	}
	
	private void addButtonCurrency() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button(myConstants.Currency());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
				    new CurrencyTypePanel().setAsMain();
				}
			});
			add(b);
		}
	}

}
