package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.JobDTO;

public class JobModel  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3875866365609331289L;
	JobDTO jobDTO;
	
	public JobModel(JobDTO jobDTO){
		this.setJobDTO(jobDTO);
	}

	private void setJobDTO(JobDTO jobDTO) {
		this.jobDTO = jobDTO;
		if(jobDTO.getStatus() != null){
			set("status", jobDTO.getStatus().toString());
		}
		if(jobDTO.getId() != null){
			set("id", jobDTO.getId());
		}
		if(jobDTO.getDeadline() != null){
			set("deadline", jobDTO.getDeadline().toString());
		}
		if(jobDTO.getTranslation() != null){
			set("translationFrom", jobDTO.getTranslation().getFrom().getLanguage());
			set("translationTo", jobDTO.getTranslation().getTo().getLanguage());
		}
		if(jobDTO.getPrice() != null){
			set("priceNumber",jobDTO.getPrice().getPrice().toString());
			if(jobDTO.getPrice().getCurrency() != null)
				set("priceCurrency",jobDTO.getPrice().getCurrency().getCurrency().toString());
		}
	}
	
	public JobDTO getJobDTO(){
		return jobDTO;
	}
	
	public String getStatus(){
		return get("status");
	}
	
	public String getDeadline(){
		return get("deadline");
	}
	
	public String getTranslationFrom(){
		return get("translationFrom");
	}
	
	public String getTranslationTo(){
		return get("translationTo");
	}
	
	public String getPriceNumber(){
		return get("priceNumber");
	}
	

	

}
