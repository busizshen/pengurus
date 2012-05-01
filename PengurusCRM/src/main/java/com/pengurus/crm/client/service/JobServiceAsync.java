package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.JobDTO;

public interface JobServiceAsync {

	public void updateJob(JobDTO jobDTO, AsyncCallback<JobDTO> callback);
	
	public void updateStatus(JobDTO jobDTO,AsyncCallback<Void> callback);

	public void getJob(Long id, AsyncCallback<JobDTO> callback);

	public void createJob(JobDTO jobDTO, AsyncCallback<JobDTO> callback);

	public void getJobByExpertId(Long id, AsyncCallback<Set<JobDTO>> callback);

	public void deleteJob(JobDTO jobDTO, AsyncCallback<Void> callback);

}
