package com.pengurus.crm.client.panels.menu;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.PengurusCRM;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelAll;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelLastEdited;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelMine;
import com.pengurus.crm.client.panels.center.quote.QuotesPanelCreate;
import com.pengurus.crm.shared.dto.UserRoleDTO;
public class QuotesMenuPanel extends TabMenuPanel {

	public QuotesMenuPanel() { 
	    super("Quotes");
        ButtonLastEdited();
	    ButtonAll();
	    ButtonMine();
	    ButtonCreate();
	}
	
	public TabMenuPanel getPanel() {
		return new QuotesMenuPanel(); 
	}

	private void ButtonCreate() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("Create New");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesPanelCreate();
				}
			});
		    add(b);
		}
	}

	private void ButtonMine() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("Mine");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesListPanelMine();
				}
			});
		    add(b);
		}
	}

	private void ButtonAll() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("All");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesListPanelAll();
				}
			});
		    add(b);
		}
	}

	private void ButtonLastEdited() {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("Last Edited");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesListPanelLastEdited();
				}
			});
		    add(b);
		} 
	}
}
