package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelQuoteView extends JobsListPanelQuote {

	public JobsListPanelQuoteView(QuoteDTO quoteDTO) {
		super(quoteDTO, 275);
		setHeading("Jobs");
		setLayout(new FitLayout());
		modelList = new ModelList(225, 825);
		add(modelList);
	}
	
}
