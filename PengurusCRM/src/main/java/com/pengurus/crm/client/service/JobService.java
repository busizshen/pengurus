package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.JobDTO;

@RemoteServiceRelativePath("job.rpc")
public interface JobService extends RemoteService{
	
	public JobDTO createJob(JobDTO jobDTO);
	public JobDTO updateJob(JobDTO jobDTO);
	public JobDTO getJob(Long id);
	public Set<JobDTO> getJobByExpertId(Long id);

}
