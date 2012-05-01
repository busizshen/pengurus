package com.pengurus.crm.client.panels.center.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelChoose;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.client.service.TranslatorService;
import com.pengurus.crm.client.service.TranslatorServiceAsync;
import com.pengurus.crm.client.service.WorkerService;
import com.pengurus.crm.client.service.WorkerServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class ProjectPanelEdit extends ProjectPanel {

	WorkerPanelChoose workersPanel;
	WorkerPanelChoose projectManagersPanel;

	public ProjectPanelEdit(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	@Override
	protected void getTranslatorsPanel(HorizontalPanel hp3) {
		AsyncCallback<Set<TranslatorDTO>> callback = new AsyncCallback<Set<TranslatorDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<TranslatorDTO> result) {
				List<UserModel> models = new ArrayList<UserModel>();
				for (UserDTO user : result) {
					models.add(new UserModel(user));
				}
				List<UserModel> modelsTo = new ArrayList<UserModel>();
				for (UserDTO user : projectDTO.getExperts()) {
					modelsTo.add(new UserModel(user));
				}
				workersPanel.init(models, modelsTo);

			}
		};

		TranslatorServiceAsync service = (TranslatorServiceAsync) GWT
				.create(TranslatorService.class);
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXPERT);
		service.getTranslatorsByRoles(roles, callback);

		workersPanel = new WorkerPanelChoose("Experts");

		workersPanel.setWidth(470);
		hp3.add(workersPanel);

	}

	
	@Override
	protected void getProjectMangaersPanel(HorizontalPanel hp3) {
		AsyncCallback<Set<WorkerDTO>> callback = new AsyncCallback<Set<WorkerDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<WorkerDTO> result) {
				List<UserModel> models = new ArrayList<UserModel>();
				for (UserDTO user : result) {
					models.add(new UserModel(user));
				}
				List<UserModel> modelsTo = new ArrayList<UserModel>();
				for (UserDTO user : projectDTO.getProjectManagers()) {
					modelsTo.add(new UserModel(user));
				}
				projectManagersPanel.init(models, modelsTo);

			}
		};

		WorkerServiceAsync service = (WorkerServiceAsync) GWT
				.create(WorkerService.class);
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		service.getWorkersByRoles(roles, callback);

		projectManagersPanel = new WorkerPanelChoose("Project Managers");
		
		projectManagersPanel.setWidth(470);
		hp3.add(projectManagersPanel);
	}

	@Override
	protected void addDescriptionPanel(VerticalPanel vp) {
		descriptionPanel = new DescriptionPanelEdit(projectDTO.getDescription(),100,300);
		vp.add(descriptionPanel);
	}

	@Override
	protected void addButtonPanel(HorizontalPanel hp2) {
		hp2.setSpacing(5);
		Button b = new Button("Update", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				projectDTO.setDescription(descriptionPanel.getDescription());
				Set<TranslatorDTO> experts = new HashSet<TranslatorDTO>();

				for (UserModel user : workersPanel.getResult()) {
					experts.add((TranslatorDTO) user.getUserDTO());
				}
				projectDTO.setExperts(experts);

				Set<WorkerDTO> projectManagers = new HashSet<WorkerDTO>();
				for (UserModel user : projectManagersPanel.getResult()) {
					projectManagers.add((WorkerDTO) user.getUserDTO());
				}
				projectDTO.setProjectManagers(projectManagers);

				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						Window.Location.assign("/spring_security_login");

					}

					@Override
					public void onSuccess(Void result) {
						MessageBox mb = new MessageBox();
						mb.setMessage("Project updated");
						mb.show();
					}
				};
				ProjectServiceAsync service = (ProjectServiceAsync) GWT
						.create(ProjectService.class);
				service.updateProject(projectDTO, callback);
			}
		});
		hp2.add(b);
		Button b1 = new Button("Cancel", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				ProjectsListPanelAll projectsList = new ProjectsListPanelAll();
				projectsList.setAsMain();// zmienić na project List Panel Main

			}
		});
		hp2.add(b1);
		Button b2 = new Button("Delete", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						Window.Location.assign("/spring_security_login");

					}

					@Override
					public void onSuccess(Void result) {
						ProjectsListPanelAll projectsList = new ProjectsListPanelAll();
						projectsList.setAsMain();// zmienić na project List
													// Panel Main

					}
				};
				ProjectServiceAsync service = (ProjectServiceAsync) GWT
						.create(ProjectService.class);
				service.deleteProject(projectDTO, callback);
			}
		});
		hp2.add(b2);
	}


}
