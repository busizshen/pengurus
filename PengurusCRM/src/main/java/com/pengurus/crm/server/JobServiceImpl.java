package com.pengurus.crm.server;

import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.entities.Job;
import com.pengurus.crm.shared.dto.JobDTO;

public class JobServiceImpl implements JobService {

	private JobDAO jobDAO;
	private TranslationDAO translationDAO;
	public JobDAO getJobDAO() {
		return jobDAO;
	}
	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
	@Override
	public JobDTO createJob(JobDTO jobDTO) {
		//translationDAO.create(new Translation(jobDTO.getTranslation()));
		jobDTO.setId(jobDAO.create(new Job(jobDTO)));
		return jobDTO;
	}
	public TranslationDAO getTranslationDAO() {
		return translationDAO;
	}
	public void setTranslationDAO(TranslationDAO translationDAO) {
		this.translationDAO = translationDAO;
	}
	@Override
	public JobDTO getJob(Long id) {
		return jobDAO.getById(id).toDTO();
	}

}
