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
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

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
	public void loadAllByExpertId() {
		// assuming db contains Expert with Id 310
		// I know that such assumption is not good in tests.
		long ExpertId = 310L;
		boolean ExpertSelected;
		List<Project> projects = projectDAO.loadAllByExpertId(ExpertId);
		Assert.notNull(projects);
		for (Project project : projects) {
			projectIsNotNull(project);
			// assert if PMId is actually PM of this Project
			ExpertSelected = false;
			for (Worker pm : project.getExperts()) {
				if (pm.getId() == ExpertId)
					ExpertSelected = true;
			}
			Assert.isTrue(ExpertSelected, "Fail, because selected project "
					+ "does not contain requiered PM id");

			for (Job job : project.getJobs()) {
				jobIsNotNull(job);
				for (Task task : job.getTask()) {
					taskIsNotNull(task);
				}
			}
		}

	}
	
	@Test
	public void getProjectByExpertId() {
		// assuming db contains Expert with Id 310
		// I know that such assumption is not good in tests.
		// if function tested above works good, here we need to check
		// only if fields aren't null
		long ExpertId = 310L;
		Set<ProjectDTO> projects = projectService
				.getProjectsByExpertId(ExpertId);
		for (ProjectDTO project : projects) {
			projectDTOIsNotNull(project);
		}
	}

	@Test
	public void loadAllByProjectManagerId() {
		// assuming db contains Project Manager with Id 313
		// I know that such assumption is not good in tests.
		long PMId = 313L;
		boolean PMSelected;
		List<Project> projects = projectDAO.loadAllByProjectManagerId(PMId);
		Assert.notNull(projects);
		for (Project project : projects) {
			projectIsNotNull(project);
			// assert if PMId is actually PM of this Project
			PMSelected = false;
			for (Worker pm : project.getProjectManagers()) {
				if (pm.getId() == PMId)
					PMSelected = true;
			}
			Assert.isTrue(PMSelected, "Fail, because selected project "
					+ "does not contain requiered PM id");

			for (Job job : project.getJobs()) {
				jobIsNotNull(job);
				for (Task task : job.getTask()) {
					taskIsNotNull(task);
				}
			}
		}
	}
	
	@Test
	public void getProjectByProjectManagerId() {
		// assuming db contains Project Manager with Id 313
		// I know that such assumption is not good in tests.
		long PMId = 313L;
		Set<ProjectDTO> projects = projectService
				.getProjectsByProjectManagerId(PMId);
		for (ProjectDTO project : projects) {
			projectDTOIsNotNull(project);
		}
	}

	@Test
	public void loadAllJobsByExpertId() {
		// assuming db contains Project Manager with Id 310
		// I know that such assumption is not good in tests.
		long ExpertId = 310L;
		boolean PMSelected;
		List<Job> jobs = jobDAO.loadAllByExpertId(ExpertId);
		Assert.notNull(jobs);
		for (Job job : jobs) {
			jobIsNotNull(job);
			// assert if ExpertId is actually in the Job
			PMSelected = false;
			for (Task task : job.getTask()) {
				taskIsNotNull(task);
				if (task.getExpert().getId() == ExpertId)
					PMSelected = true;
			}
			Assert.isTrue(PMSelected, "Fail, because selected project "
					+ "does not contain requiered PM id");
		}
	}

	@Test
	public void getJobsByExpertId() {
		// tested function is based on the loadAllJobsByExpertId() function
		// so far that function works, this works too
		// checking only if fields aren't null
		long ExpertId = 310L;
		Set<JobDTO> jobs = jobService.getJobByExpertId(ExpertId);
		for (JobDTO jobDTO : jobs)
			jobDTOIsNotNull(jobDTO);
	}
	
	/*@Test
	public void loadProjectByTaskId() {
	/*	// assuming db contains Task with Id 318
		// I know that such assumption is not good in tests.
		long taskId = 318L;
		boolean taskSelected = false;
		List<Project> projects = projectDAO.loadAllByTaskId(taskId);
		Assert.notNull(projects);
		// there should be only one Project containing the taskId
		Assert.isTrue(projects.size() == 1);
		for (Project project : projects) {
			projectIsNotNull(project);
			for (Job job : project.getJobs()) {
				jobIsNotNull(job);
				for (Task task : job.getTask()) {
					taskIsNotNull(task);
					if (task.getId() == taskId) {
						taskSelected = true;
					}
				}
			}
		}
		Assert.isTrue(taskSelected, "Fail, because selected project "
				+ "does not contain requiered task");

	}
*/
/*	@Test
	public void getProjectByTaskId(){
		//jeszcze raz napisać
/*		// assuming db contains Task with Id 318
		// I know that such assumption is not good in tests.
		long taskId = 318L;
		Set<ProjectDTO> projects = projectService.getProjectByTaskId(taskId);
		Assert.isTrue(projects.size() == 1);
		for(ProjectDTO project : projects){
			projectDTOIsNotNull(project);
<<<<<<< Updated upstream
		}
	}*/
	
	public void jobIsNotNull(Job job) {
		Assert.notNull(job.getId(), "null Id in Job");
		Assert.notNull(job.getAmount(), "null Amount in Job");
		Assert.notNull(job.getDeadline(), "null Deadline in Job");
		Assert.notNull(job.getPrice(), "null Price in Job");
		Assert.notNull(job.getStatus(), "null Status in Job");
		Assert.notNull(job.getTask(), "null Task in Job");
	}

	public void jobDTOIsNotNull(JobDTO job) {
		Assert.notNull(job.getId(), "null Id in Job");
		Assert.notNull(job.getAmount(), "null Amount in Job");
		Assert.notNull(job.getDeadline(), "null Deadline in Job");
		Assert.notNull(job.getPrice(), "null Price in Job");
		Assert.notNull(job.getStatus(), "null Status in Job");
	}

	public void taskIsNotNull(Task task) {
		Assert.notNull(task.getId(), "null Id in Task");
		Assert.notNull(task.getAmount(), "null Amount in Task");
		Assert.notNull(task.getDeadline(), "null Deadline in Task");
		Assert.notNull(task.getPrice(), "null Price in Task");
		Assert.notNull(task.getStatus(), "null Status in Task");
		Assert.notNull(task.getExpert(), "null Expert in Task");
	}

	public void taskDTOIsNotNull(TaskDTO task) {
		Assert.notNull(task.getId(), "null Id in Task");
		Assert.notNull(task.getAmount(), "null Amount in Task");
		Assert.notNull(task.getDeadline(), "null Deadline in Task");
		Assert.notNull(task.getPrice(), "null Price in Task");
		Assert.notNull(task.getStatus(), "null Status in Task");
		Assert.notNull(task.getExpert(), "null Expert in Task");
	}

	public void projectIsNotNull(Project project) {
		Assert.notNull(project.getClient(), "null Client in Project");
		Assert.notNull(project.getId(), "null Id in Project");
		Assert.notNull(project.getSupervisor(), "null Supervisor in Project");
		Assert.notNull(project.getExperts(), "null Experts in Project");
		Assert.notNull(project.getFreelancers(), "null Experts in Project");
		Assert.notNull(project.getProjectManagers(), "null Experts in Project");
	}

	public void projectDTOIsNotNull(ProjectDTO project) {
		Assert.notNull(project.getClient(), "null Client in Project");
		Assert.notNull(project.getId(), "null Id in Project");
		Assert.notNull(project.getSupervisor(), "null Supervisor in Project");
	}

}
