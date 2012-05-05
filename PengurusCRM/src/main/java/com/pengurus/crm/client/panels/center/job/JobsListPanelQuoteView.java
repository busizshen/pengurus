package com.pengurus.crm.client.panels.center.job;

import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelQuoteView extends JobsListPanelQuote {

	public JobsListPanelQuoteView(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	public MainPanel getPanel() {
		setHeading("Jobs");
		modelList = new ModelList();
		add(modelList);
		return this;
	}
	
}
