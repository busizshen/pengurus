package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

public interface PaginationServiceAsync {

	public void getPaginatedAllProjects(PagingLoadConfigHelper loadConfig, 
			AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	public void getPaginatedProjectsByExpertId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	public void getPaginatedProjectsByProjectManagerId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	public void getPaginatedProjectsBySupervisorId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);

	public void getPaginatedAllQuotes(PagingLoadConfigHelper loadConfig,
			AsyncCallback<PagingLoadResultHelper<QuoteModel>> callback);
	public void getPaginatedQuotesBySupervisorId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<QuoteModel>> callback);
	public void getPaginatedQuotesByClientId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<QuoteModel>> callback);
	
	public void getPaginatedAllTasks(PagingLoadConfigHelper loadConfig,
			AsyncCallback<PagingLoadResultHelper<TaskModel>> callback);
	public void getPaginatedTasksByExpertId(PagingLoadConfigHelper loadConfig, Long id,
			AsyncCallback<PagingLoadResultHelper<TaskModel>> callback);

	public void getPaginatedAllUsers(PagingLoadConfigHelper loadConfig,
			AsyncCallback<PagingLoadResultHelper<UserModel>> callback);
	public void getPaginatedUsersByRoles(PagingLoadConfigHelper loadConfig,
			Set<UserRoleDTO> roles,
			AsyncCallback<PagingLoadResultHelper<UserModel>> callback);
	
}
