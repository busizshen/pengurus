package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.JobDTO;

public interface JobServiceAsync {
	public void createJob(JobDTO jobDTO,AsyncCallback<JobDTO> callback);
	public void getJob(Long id,AsyncCallback<JobDTO> callback);
}
