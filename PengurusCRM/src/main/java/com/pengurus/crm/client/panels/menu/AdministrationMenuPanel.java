package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.AuthorizationManager;


public class AdministrationMenuPanel extends TabMenuPanel {
	
	public AdministrationMenuPanel() {
		super("Administration");
	    addButtonTranslations();
	    addButtonLanguages();
	    addButtonTranslationTypes();
	    addButtonCurrency();
	}
	
	private void addButtonTranslations() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button("Translations");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					//
				}
			});
			add(b);
		}
	}
	
	private void addButtonLanguages() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button("Languages");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					//
				}
			});
			add(b);
		}
	}
	
	private void addButtonTranslationTypes() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button("Translation Types");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					//
				}
			});
			add(b);
		}
	}
	
	private void addButtonCurrency() {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button("Currency");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					//
				}
			});
			add(b);
		}
	}

}
