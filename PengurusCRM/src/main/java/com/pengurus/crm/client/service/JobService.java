package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.JobDTO;

@RemoteServiceRelativePath("job.rpc")
public interface JobService extends RemoteService{
	public JobDTO createJob(JobDTO jobDTO);

}
