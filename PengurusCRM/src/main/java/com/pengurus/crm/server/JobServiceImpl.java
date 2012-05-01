package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.exceptions.DeleteException;
import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.entities.Job;
import com.pengurus.crm.enums.Status;
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
		// translationDAO.create(new Translation(jobDTO.getTranslation()));
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

	@Override
	public JobDTO updateJob(JobDTO jobDTO) {
		Job j = new Job(jobDTO);
		jobDAO.update(j);
		return j.toDTO();
	}

	@Override
	public Set<JobDTO> getJobByExpertId(Long id) {
		List<Job> list = jobDAO.loadAllByExpertId(id);
		Set<JobDTO> set = new HashSet<JobDTO>();
		for (Job job : list) {
			set.add(job.toDTOLazy());
		}
		return set;
	}

	@Override
	public void deleteJob(JobDTO jobDTO) throws DeleteException {

		if (!jobDAO.delete(new Job(jobDTO))) {
			throw new DeleteException();
		}

	}

	@Override
	public void updateStatus(JobDTO jobDTO) {
			Job job = jobDAO.read(jobDTO.getId());
			job.setStatus(Status.toStatus(jobDTO.getStatus()));
			jobDAO.update(job);		
	}
}
