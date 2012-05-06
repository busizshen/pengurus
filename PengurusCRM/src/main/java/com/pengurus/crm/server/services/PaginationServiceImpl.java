package com.pengurus.crm.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.service.PaginationService;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.pagination.PaginationUtils;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

public class PaginationServiceImpl implements PaginationService {

	private QuoteService quoteService;
	private ProjectService projectService;
	private TaskService taskService;
	private UserService userService;
	
	public QuoteService getQuoteService() {
		return quoteService;
	}
	public void setQuoteService(QuoteService quoteService) {
		this.quoteService = quoteService;
	}
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public PagingLoadResultHelper<ProjectModel> getPaginatedAllProjects(
			PagingLoadConfigHelper loadConfig) {
	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
	for (ProjectDTO projectDTO : projectService.getAllProjects()) {
		projectModelList.add(new ProjectModel(projectDTO));
	}
	return PaginationUtils.paginate(loadConfig, projectModelList);
	}

	@Override
	public PagingLoadResultHelper<ProjectModel> getPaginatedProjectsByExpertId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		for (ProjectDTO projectDTO : projectService.getProjectsByExpertId(id)) {
			projectModelList.add(new ProjectModel(projectDTO));
		}
		return PaginationUtils.paginate(loadConfig, projectModelList);
	}

	@Override
	public PagingLoadResultHelper<ProjectModel> getPaginatedProjectsByProjectManagerId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		for (ProjectDTO projectDTO : projectService.getProjectsByProjectManagerId(id)) {
			projectModelList.add(new ProjectModel(projectDTO));
		}
		return PaginationUtils.paginate(loadConfig, projectModelList);
	}

	@Override
	public PagingLoadResultHelper<ProjectModel> getPaginatedProjectsBySupervisorId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		for (ProjectDTO projectDTO : projectService.getProjectsBySupervisorId(id)) {
			projectModelList.add(new ProjectModel(projectDTO));
		}
		return PaginationUtils.paginate(loadConfig, projectModelList);
	}

	@Override
	public PagingLoadResultHelper<QuoteModel> getPaginatedAllQuotes(
			PagingLoadConfigHelper loadConfig) {
		List<QuoteModel> quoteModelList = new ArrayList<QuoteModel>();
		for (QuoteDTO quoteDTO : quoteService.getAllQuotes()) {
			quoteModelList.add(new QuoteModel(quoteDTO));
		}
		return PaginationUtils.paginate(loadConfig, quoteModelList);
	}

	@Override
	public PagingLoadResultHelper<QuoteModel> getPaginatedQuotesBySupervisorId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<QuoteModel> quoteModelList = new ArrayList<QuoteModel>();
		for (QuoteDTO quoteDTO : quoteService.getQuotesBySupervisorId(id)) {
			quoteModelList.add(new QuoteModel(quoteDTO));
		}
		return PaginationUtils.paginate(loadConfig, quoteModelList);
	}

	@Override
	public PagingLoadResultHelper<QuoteModel> getPaginatedQuotesByClientId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<QuoteModel> quoteModelList = new ArrayList<QuoteModel>();
		for (QuoteDTO quoteDTO : quoteService.getQuotesByClientId(id)) {
			quoteModelList.add(new QuoteModel(quoteDTO));
		}
		return PaginationUtils.paginate(loadConfig, quoteModelList);
	}

	@Override
	public PagingLoadResultHelper<TaskModel> getPaginatedAllTasks(
			PagingLoadConfigHelper loadConfig) {
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		for (TaskDTO user : taskService.getAllTasks()) {
			taskModelList.add(new TaskModel(user));
		}
		return PaginationUtils.paginate(loadConfig, taskModelList);
	}

	@Override
	public PagingLoadResultHelper<TaskModel> getPaginatedTasksByExpertId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		for (TaskDTO user : taskService.getTasksByExpertId(id)) {
			taskModelList.add(new TaskModel(user));
		}
		return PaginationUtils.paginate(loadConfig, taskModelList);
	}
	
	@Override
	public PagingLoadResultHelper<UserModel> getPaginatedAllUsers(PagingLoadConfigHelper loadConfig) {
		List<UserModel> userModelList = new ArrayList<UserModel>();
		for (UserDTO user:userService.getAllUsers()) {
			userModelList.add(new UserModel(user));
		}
		return PaginationUtils.paginate(loadConfig, userModelList);
	}

	@Override
	public PagingLoadResultHelper<UserModel> getPaginatedUsersByRoles(
			PagingLoadConfigHelper loadConfig, Set<UserRoleDTO> roles) {
		List<UserModel> userModelList = new ArrayList<UserModel>();
		for (UserDTO user:userService.getUsersByRoles(roles)) {
			userModelList.add(new UserModel(user));
		}
		return PaginationUtils.paginate(loadConfig, userModelList);
	}
	@Override
	public PagingLoadResultHelper<TaskModel> getPaginatedTasksByReviewerId(
			PagingLoadConfigHelper loadConfig, Long id) {
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		for (TaskDTO user : taskService.getTasksByReviewerId(id)) {
			taskModelList.add(new TaskModel(user));
		}
		return PaginationUtils.paginate(loadConfig, taskModelList);

	}

}
