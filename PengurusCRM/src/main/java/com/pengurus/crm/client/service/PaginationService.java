package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

@RemoteServiceRelativePath("pagination.rpc")
public interface PaginationService extends RemoteService {

	public PagingLoadResultHelper<ProjectModel> getPaginatedAllProjects(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<ProjectModel> getPaginatedProjectsByExpertId(PagingLoadConfigHelper loadConfig, Long id);
	public PagingLoadResultHelper<ProjectModel> getPaginatedProjectsByProjectManagerId(PagingLoadConfigHelper loadConfig, Long id);
	public PagingLoadResultHelper<ProjectModel> getPaginatedProjectsBySupervisorId(PagingLoadConfigHelper loadConfig, Long id);
	
	public PagingLoadResultHelper<QuoteModel> getPaginatedAllQuotes(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<QuoteModel> getPaginatedQuotesBySupervisorId(PagingLoadConfigHelper loadConfig, Long id);
	public PagingLoadResultHelper<QuoteModel> getPaginatedQuotesByClientId(PagingLoadConfigHelper loadConfig, Long id);

	public PagingLoadResultHelper<TaskModel> getPaginatedAllTasks(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<TaskModel> getPaginatedTasksByExpertId(PagingLoadConfigHelper loadConfig, Long id);
	
	public PagingLoadResultHelper<UserModel> getPaginatedAllUsers(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<UserModel> getPaginatedUsersByRoles(PagingLoadConfigHelper loadConfig, Set<UserRoleDTO> roles);

}
