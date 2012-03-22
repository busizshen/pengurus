package com.pengurus.crm.server;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.daos.ProjectDAO;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.Job;
import com.pengurus.crm.entities.Project;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;

@ContextConfiguration(locations = { "testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class GettingObjectsById {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProjectDAO projectDAO;
	
	@Autowired
	JobDAO jobDAO;

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	@Test
	public void getProjectByExpertId() { 
		Set<ProjectDTO> projects = projectService.getProjectByExpertId(310L);
		for(ProjectDTO projectDTO : projects)
			Assert.notNull(projectDTO);
	}
	
	@Test
	public void loadAllByExpertId() { 
		List<Project> projects = projectDAO.loadAllByExpertId(310L);
		Assert.notNull(projects);
		for(Project project : projects)
			Assert.notNull(project);		
	}
	
	@Test
	public void getProjectByProjectManagerId() { 
		Set<ProjectDTO> projects = projectService.getProjectByProjectManagerId(313L);
		for(ProjectDTO projectDTO : projects)
			Assert.notNull(projectDTO);
	}
	
	@Test
	public void loadAllByProjectManagerId() { 
		List<Project> projects = projectDAO.loadAllByProjectManagerId(313L);
		Assert.notNull(projects);
		for(Project project : projects)
			Assert.notNull(project);		
	}

	@Test
	public void getJobsByExpertId() { 
		Set<JobDTO> jobs = jobService.getJobByExpertId(310L);
		for(JobDTO jobDTO : jobs)
			Assert.notNull(jobDTO);
	}
	
	@Test
	public void loadAllJobsByExpertId() { 
		List<Job> jobs = jobDAO.loadAllByExpertId(310L);
		Assert.notNull(jobs);
		for(Job job : jobs)
			Assert.notNull(job);		
	}
	
}
