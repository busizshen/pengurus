package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelQuoteView extends JobsListPanelQuote {

	public JobsListPanelQuoteView(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	public ContentPanel getPanel() {
		setHeading("Jobs");
		modelList = new ModelList();
		add(modelList);
		return this;
	}
	
}
