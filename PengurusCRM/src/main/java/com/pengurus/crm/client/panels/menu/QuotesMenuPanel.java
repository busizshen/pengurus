package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.client.panels.center.quote.QuotePanelCreate;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelAll;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelByClient;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelByUser;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelByWorker;

public class QuotesMenuPanel extends TabMenuPanel {
	private static i18nConstants myConstants=(i18nConstants)GWT.create(i18nConstants.class);

	public QuotesMenuPanel() {
		super(myConstants.Quotes());
		addButtonAll();
		addButtonMine();
		addButtonCreate();
	}

	public TabMenuPanel getPanel() {
		return new QuotesMenuPanel();
	}

	private void addButtonCreate() {
		if (AuthorizationManager.canViewAllQuotes()) {
			Button b = new Button(myConstants.CreateNew());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					QuotePanelCreate qp = new QuotePanelCreate();
					qp.setAsMain();
				}
			});
			add(b);
		}
	}

	private void addButtonMine() {
		if (AuthorizationManager.canViewQuotes()) {
			Button b = new Button(myConstants.Mine());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					QuotesListPanelByUser qlp;
					if (AuthorizationManager.isClient())
						qlp = new QuotesListPanelByClient(AuthorizationManager
								.getCurrentUser());
					else
						qlp = new QuotesListPanelByWorker(AuthorizationManager
								.getCurrentUser());
					qlp.setAsMain();

				}
			});
			add(b);
		}
	}

	private void addButtonAll() {
		if (AuthorizationManager.canViewAllQuotes()) {
			Button b = new Button(myConstants.All());
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					QuotesListPanelAll qlp = new QuotesListPanelAll();
					qlp.setAsMain();
				}
			});
			add(b);
		}
	}

}
