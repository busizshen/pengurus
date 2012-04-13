package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelQuoteView extends JobsListPanelQuote {

	ModelList ml;

	public JobsListPanelQuoteView(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	public ContentPanel getPanel() {
		setHeading("Jobs");
		setCollapsible(true);
		setAnimCollapse(true);
		collapse();
		ml = new ModelList();
		add(ml);
		return this;
	}


}