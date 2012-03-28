package com.pengurus.crm.client.panels.center.job;

import com.pengurus.crm.shared.dto.JobDTO;

public class JobPanelQuote extends JobPanel {

	public JobPanelQuote(JobDTO jobDTO) {
		super(jobDTO);
	}

	@Override
	protected void setTranslation() {
		if(translation.getTranslation().getTranslationDTO() != jobDTO.getTranslation()){
			jobDTO.setTranslation(translation.getTranslation().getTranslationDTO());
		}
	}
}
