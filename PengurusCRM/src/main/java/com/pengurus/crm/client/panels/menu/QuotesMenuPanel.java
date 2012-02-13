package com.pengurus.crm.client.panels.menu;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.PengurusCRM;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelAll;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelLastEdited;
import com.pengurus.crm.client.panels.center.quote.QuotesListPanelMine;
import com.pengurus.crm.client.panels.center.quote.QuotesPanelCreate;
import com.pengurus.crm.shared.dto.UserRoleDTO;
public class QuotesMenuPanel extends TabMenuPanel {

	public ContentPanel getPanel() {
		panel = new ContentPanel();  
	    panel.setAnimCollapse(false);  
	    panel.setHeading("QUOTES");
	    setPanelStyle();
        ButtonLastEdited(panel);
	    ButtonAll(panel);
	    ButtonMine(panel);
	    ButtonCreate(panel);
		return panel;

}

	private void ButtonCreate(ContentPanel panel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("Create New");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesPanelCreate();
				}
			});
			setButtonStyle(b);
		    panel.add(b);
		}
	}

	private void ButtonMine(ContentPanel panel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("Mine");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesListPanelMine();
				}
			});
			setButtonStyle(b);
		    panel.add(b);
		}
	}

	private void ButtonAll(ContentPanel panel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("All");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesListPanelAll();
				}
			});
			setButtonStyle(b);
		    panel.add(b);
		}
	}

	private void ButtonLastEdited(ContentPanel panel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
			Button b = new Button("Last Edited");
			b.addSelectionListener(new SelectionListener<ButtonEvent>(){
				@Override
				public void componentSelected(ButtonEvent ce) {
					new QuotesListPanelLastEdited();
				}
			});
			setButtonStyle(b);
		    panel.add(b);
		} 
	}
}
