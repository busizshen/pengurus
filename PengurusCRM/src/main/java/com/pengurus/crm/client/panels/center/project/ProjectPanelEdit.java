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
import com.pengurus.crm.client.panels.center.user.UserPanel.UserViewInfo;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelChoose;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class ProjectPanelEdit extends ProjectPanel {

	ClientPanelView client;
	WorkerPanelChoose workersPanel;

	public ProjectPanelEdit(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	@Override
	protected void addTranslatorsPanel() {
		AsyncCallback<Set<UserDTO>> callback = new AsyncCallback<Set<UserDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<UserDTO> result) {
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

		UserServiceAsync service = (UserServiceAsync) GWT
				.create(UserService.class);
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXPERT);
		service.getUsersByRoles(roles, callback);

		workersPanel = new WorkerPanelChoose("Experts");
		add(workersPanel);

	}

	@Override
	protected void addProjectMangaersPanel() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void addDescriptionPanel(HorizontalPanel hp) {
		descriptionPanel = new DescriptionPanelEdit(projectDTO.getDescription());
		descriptionPanel.setWidth(300);
		hp.add(descriptionPanel);
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
				/*
				 * Set<WorkerDTO> projectManagers = new HashSet<WorkerDTO>();
				 * for(UserModel user : workersPanel.getResult()){
				 * projectManagers .add((TranslatorDTO) user.getUserDTO()); }
				 * projectDTO.setProjectManagers(projectManagers);
				 */
				/*
				 * Set<TranslatorDTO> freelancers = new
				 * HashSet<TranslatorDTO>();
				 * projectDTO.setFreelancers(freelancers);
				 */

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
				projectsList.setAsMain();//zmienić na project List Panel Main
				
			}});
		hp2.add(b1);
		Button b2 = new Button("Delete",new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						Window.Location.assign("/spring_security_login");

					}

					@Override
					public void onSuccess(Void result) {
						ProjectsListPanelAll projectsList = new ProjectsListPanelAll();
						projectsList.setAsMain();//zmienić na project List Panel Main
						
					}
				};
				ProjectServiceAsync service = (ProjectServiceAsync) GWT
						.create(ProjectService.class);
				service.deleteProject(projectDTO, callback);	
			}});
		hp2.add(b2);
	}



	@Override
	protected void addClientPanel(VerticalPanel vp) {
		/*
		 * if(AuthorizationManager.canViewWholeProject()){ Listener<DomEvent>
		 * listener2 = new Listener<DomEvent>() {
		 * 
		 * @Override public void handleEvent(DomEvent be) {
		 * projectDTO.setClient(client.getChosenClient());
		 * client.refresh(projectDTO.getClient()); } }; client = new
		 * ClientPanelEdit(projectDTO.getClient(),listener2); UserViewInfo
		 * clientPanel = client.getInfoPanel();
		 * clientPanel.setHeading("Client"); clientPanel.expand();
		 * vp.add(clientPanel); }
		 */
		client = new ClientPanelView(projectDTO.getClient());
		UserViewInfo clientPanel = client.getInfoPanel();
		clientPanel.setHeading("Client");
		clientPanel.expand();
		clientPanel.setCollapsible(false);
		vp.add(clientPanel);
		
	}

	@Override
	protected void addSupervisorPanel(VerticalPanel vp) {
		/*
		 * Listener<DomEvent> listener = new Listener<DomEvent>() {
		 * 
		 * @Override public void handleEvent(DomEvent be) {
		 * projectDTO.setSupervisor(supervisor.getChosenWorker());
		 * supervisor.refresh(projectDTO.getSupervisor()); } }; supervisor = new
		 * WorkerPanelEdit(projectDTO.getSupervisor(), listener); UserViewInfo
		 * supervisorPanel = supervisor.getInfoPanel();
		 * supervisorPanel.setHeading("Supervisor"); supervisorPanel.expand();
		 * vp.add(supervisorPanel);
		 */
		supervisor = new WorkerPanelView(projectDTO.getSupervisor());
		UserViewInfo supervisorPanel = supervisor.getInfoPanel();
		supervisorPanel.setHeading("Supervisor");
		supervisorPanel.expand();
		supervisorPanel.setCollapsible(false);
		vp.add(supervisorPanel);
	}

}
