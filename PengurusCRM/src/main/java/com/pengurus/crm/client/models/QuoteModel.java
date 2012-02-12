package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuoteModel extends BaseModel {
	
	private static final long serialVersionUID = -274993927725641112L;

	private QuoteDTO quoteDTO;
	public QuoteModel(QuoteDTO quoteDTO){
		setQuoteDTO(quoteDTO);
	}
	
	public void setQuoteDTO(QuoteDTO quoteDTO){
		this.quoteDTO = quoteDTO;
		if(quoteDTO.getStatus() != null)
			set("status",quoteDTO.getStatus().toString());
		if(quoteDTO.getClient() != null)
			set("client",quoteDTO.getClient().getUsername());
		if(quoteDTO.getSupervisor() != null)
			set("supervisor",quoteDTO.getSupervisor().getUsername());
		set("description",quoteDTO.getDescription());
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
	
}
