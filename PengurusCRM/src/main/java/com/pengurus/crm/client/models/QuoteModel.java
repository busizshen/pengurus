package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuoteModel extends BaseModel {
	
	private static final long serialVersionUID = -274993927725641112L;

	private QuoteDTO quoteDTO;
	public QuoteModel(QuoteDTO quoteDTO){
		setQuoteDTO(quoteDTO);
	}
	
	public void setQuoteDTO(QuoteDTO quoteDTO){
		this.quoteDTO = quoteDTO;
		if(quoteDTO.getId() != null)
			set("id",quoteDTO.getId().toString());
		if(quoteDTO.getStatus() != null)
			set("status",quoteDTO.getStatus().toString());
		if(quoteDTO.getClient() != null)
			set("client",quoteDTO.getClient().getUsername());
		if(quoteDTO.getSupervisor() != null)
			set("supervisor",quoteDTO.getSupervisor().getUsername());
		set("description",quoteDTO.getDescription());
	}

	public String getId(){
		return get("id");
	}
	
	public String getStatus(){
		return get("status");
	}
	
	public String getClient(){
		return get("client");
	}
	
	public String getSupervisor(){
		return get("supervisor");
	}
	
	public String getDescription(){
		return get("descritpion");
	}

	public QuoteDTO getQuoteDTO() {
		return this.quoteDTO;
	}

	public QuoteDTO getQuoteDTONonLazy() {
		AsyncCallback<QuoteDTO> callback = new AsyncCallback<QuoteDTO>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(QuoteDTO result) {
				quoteDTO = result;
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		service.getQuote(quoteDTO.getId(),callback);
		MessageBox mb = new MessageBox();
		mb.setMessage(quoteDTO.getId().toString());
		mb.show();
		return quoteDTO;//jakie quote zwróci
	}
	
}
