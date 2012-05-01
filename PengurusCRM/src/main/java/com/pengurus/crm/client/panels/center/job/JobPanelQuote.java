package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.panels.center.quote.QuotePanel;
import com.pengurus.crm.client.panels.center.quote.QuotePanelView;
import com.pengurus.crm.client.panels.center.status.JobStatusPanel;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobPanelQuote extends JobPanel {

	private QuoteDTO quoteDTO;

	public JobPanelQuote(JobDTO jobDTO, QuoteDTO quoteDTO) {
		super(jobDTO);
		this.quoteDTO = quoteDTO;
	}

	@Override
	protected void setTranslation() {
		if(translation.getTranslation().getTranslationDTO() != jobDTO.getTranslation()){
			jobDTO.setTranslation(translation.getTranslation().getTranslationDTO());
		}
	}

	@Override
	protected void cancelButton() {
		AsyncCallback<QuoteDTO> callback = new AsyncCallback<QuoteDTO>() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error Cancel");
				mb.show();
			}

			@Override
			public void onSuccess(QuoteDTO result) {
				QuotePanel quotePanel = new QuotePanelView(result);
				quotePanel.setAsMain();
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		service.getQuote(quoteDTO.getId(), callback);
		
	}

	@Override
	protected void addStatusPanel(VerticalPanel vp) {
	}
}