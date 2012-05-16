package com.pengurus.crm.client.panels.center.status;

import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobStatusPanelQuote extends JobStatusPanel{

	QuoteDTO quoteDTO;
	public JobStatusPanelQuote(JobDTO jobDTO, QuoteDTO quoteDTO) {
		super(jobDTO);
		this.quoteDTO = quoteDTO;
		initPanel();
	}

	@Override
	protected void setVisibility() {
		nextStatus.setVisible(status != 3 ? false : AuthorizationManager
				.hasClientAccess(quoteDTO) );
		reOpen.setVisible(status != 3 ? false : AuthorizationManager
				.hasClientAccess(quoteDTO));

		refresh();
	}

}
