package com.pengurus.crm.server;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.pengurus.crm.client.service.exceptions.IOException;
import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.daos.ProjectDAO;
import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Job;
import com.pengurus.crm.entities.Project;
import com.pengurus.crm.entities.Quote;
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.enums.FileType;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class PermissionEvaluatorImpl implements PermissionEvaluator {

	final Logger logger = LoggerFactory
			.getLogger(PermissionEvaluatorImpl.class);

	private TaskDAO taskDAO;
	private JobDAO jobDAO;
	private QuoteDAO quoteDAO;
	private ProjectDAO projectDAO;

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	public QuoteDAO getQuoteDAO() {
		return quoteDAO;
	}

	public void setQuoteDAO(QuoteDAO quoteDAO) {
		this.quoteDAO = quoteDAO;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {

		if (targetDomainObject == null) {
			logger.error("null conditions must be handled directly in the security expression");
			return false;
		} else if (authentication.getName() == null) {
			logger.error("anonymous conditions must be handled directly in the security expression");
			return false;
		} else if (!(permission instanceof String)) {
			logger.error("unknown permission type: {}", permission);
			return false;
		}

		if (targetDomainObject instanceof UserDTO) {
			return hasPermissionToUser(authentication,
					(UserDTO) targetDomainObject, permission);
		} else if (targetDomainObject instanceof ProjectDTO) {
			return hasPermissionToProject(authentication.getName(),
					(ProjectDTO) targetDomainObject, permission);
		} else if (targetDomainObject instanceof QuoteDTO) {
			return hasPermissionToQuote(authentication.getName(),
					(QuoteDTO) targetDomainObject, permission);
		} else if (targetDomainObject instanceof TaskDTO) {
			return hasPermissionToTask(authentication.getName(),
					(TaskDTO) targetDomainObject, permission);
		}
		return false;
	}

	private boolean hasPermissionToUser(Authentication authentication,
			UserDTO user, Object permission) {
		String username = authentication.getName();
		if ("read".equals(permission)) {
			return username.equals(user.getUsername())
					|| user.getAuthorities().contains(
							UserRoleDTO.ROLE_EXECUTIVE)
					|| containRole(authentication.getAuthorities(),
							UserRoleDTO.ROLE_WORKER.toString());
		} else if ("write".equals(permission)) {
			return username.equals(user.getUsername());
		}
		return false;
	}

	private boolean hasPermissionToProject(String username, ProjectDTO project,
			Object permission) {
		if ("read".equals(permission)) {
			return userDTOsContainUsername(project.getExperts(), username)
					|| userDTOsContainUsername(project.getFreelancers(),
							username)
					|| userDTOsContainUsername(project.getProjectManagers(),
							username);
		} else if ("write".equals(permission)) {
			return userDTOsContainUsername(project.getProjectManagers(),
					username);
		}
		return false;
	}

	private boolean hasPermissionToQuote(String name, QuoteDTO quote,
			Object permission) {
		if (quote.getClient() != null) {
			return quote.getClient().getUsername().equals(name);
		}
		return false;
	}

	private boolean hasPermissionToTask(String username, TaskDTO task,
			Object permission) {
		boolean result = false;
		if (task.getExpert() != null) {
			result |= task.getExpert().getUsername().equals(username);
		}
		if (task.getReviewer() != null) {
			result |= task.getReviewer().getUsername().equals(username);
		}
		return result;
	}

	private boolean hasPermissionToFile(Authentication authentication, long quoteId, long jobId, long taskId, FileType fileType, String permission) throws IOException {
		
		Task task = null;
		Job job = null;
		Quote quote = null;
		Project project = null;
		
		if (taskId != 0) {
			if (jobId == 0) {
				throw new IOException();
			}
			task = taskDAO.read(taskId);
		}
		if (jobId != 0) {
			job = jobDAO.read(jobId);
			project = projectDAO.loadByJobId(jobId);
		}
		if (quoteId != 0) {
			quote = quoteDAO.read(quoteId);
		} else {
			throw new IOException();
		}
		
		if (taskId != 0) {
			if (!job.getTask().contains(task)) {
				throw new IOException();
			}
		}
		if (jobId != 0) {
			if (!quote.getJobs().contains(job)) {
				throw new IOException();
			}
		}
		
		if (taskId != 0) {
			if (containRole(authentication.getAuthorities(), UserRoleDTO.ROLE_EXECUTIVE.toString())) {
				return true;
			}
			if (usersContainUsername(project.getProjectManagers(), authentication.getName())) {
				return true;
			}
			if (fileType.equals(FileType.output)) {
				if ("upload".equals(permission)) {
					if (task.getExpert().getUsername().equals(authentication.getName()) ||
							task.getReviewer().getUsername().equals(authentication.getName())) {
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean containRole(
			Collection<? extends GrantedAuthority> authorities, String role) {
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}

	private boolean userDTOsContainUsername(
			Collection<? extends UserDTO> users, String username) {
		for (UserDTO user : users) {
			if (username.equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

	private boolean usersContainUsername(Collection<? extends User> users,
			String username) {
		for (User user : users) {
			if (username.equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
}
