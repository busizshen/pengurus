package com.pengurus.crm.client.panels.center.task;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.RatingPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.job.JobPanelProject;
import com.pengurus.crm.client.panels.center.status.TaskStatusPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByList;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class TaskPanel extends MainPanel {

	protected TaskDTO taskDTO;
	protected ProjectDTO projectDTO;
	protected NumberField amount;
	protected NumberField price;
	protected ComboBox<CurrencyModel> combo;
	protected DescriptionPanel description;
	protected TranslationPanel translation;
	protected FormPanel mainPanel;
	protected DateField deadline;
	protected WorkerPanel workerPanel;
	TaskStatusPanel statusBar;
	RatingPanel rating;

	public TaskPanel(TaskDTO task, ProjectDTO jobDTO) {
		this.taskDTO = task;
		this.projectDTO = jobDTO;
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		mainPanel = new FormPanel();
		mainPanel.setFrame(false);
		mainPanel.setHeaderVisible(false);
		VerticalPanel vp1 = new VerticalPanel();
		vp1.setSpacing(10);
		addButtonPanel(vp1);
		addStatusBar(vp1);
		addInfoPanel(vp1);
		hp.add(vp1);
		VerticalPanel vp2 = new VerticalPanel();
		vp2.setSpacing(10);
		addRatingPanel(vp2);
		addDescriptionPanel(vp2);
		hp.add(vp2);
		mainPanel.add(hp);
		addTranslatorPanel(mainPanel);
		add(mainPanel);

	}

	protected void setStyle(HorizontalPanel hp) {
		hp.setSpacing(5);
		hp.setAutoHeight(true);
		hp.setAutoWidth(true);
	}

	private void addTranslatorPanel(FormPanel vp) {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Set<WorkerDTO> translators = new HashSet<WorkerDTO>();
			translators.addAll(projectDTO.getExperts());
			workerPanel = new WorkerPanelEditByList(taskDTO.getExpert(),"Translator",translators);
		} else {
			workerPanel = new WorkerPanelView(taskDTO.getExpert(),"Translator");
		}
		vp.add(workerPanel);

	}

	protected void addStatusBar(VerticalPanel vp1) {
		statusBar = new TaskStatusPanel(taskDTO);
		vp1.add(statusBar);
	}

	protected void addDescriptionPanel(VerticalPanel vp2) {
		description = new DescriptionPanelEdit(taskDTO.getDescription());
		description.setWidth(300);
		vp2.add(description);
	}

	protected void addRatingPanel(VerticalPanel vp2) {
		rating = new RatingPanel();
		vp2.add(rating);
	}

	private void addInfoPanel(VerticalPanel vp1) {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setAutoHeight(true);
		simple.setAutoWidth(true);
		simple.setHeaderVisible(false);
		if (AuthorizationManager.canEditProject(projectDTO)) {

			final ListStore<CurrencyModel> listCurrencyModel = new ListStore<CurrencyModel>();
			amount = new NumberField();
			amount.setFieldLabel("Amount");
			amount.setName("amount");
			amount.setData("text", "Enter your amount");

			price = new NumberField();
			price.setFieldLabel("Price");
			price.setName("price");
			price.setData("text", "Enter your price and choose Currnecy");

			combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setData("text", "Choose Language");
			combo.setStore(listCurrencyModel);

			if (taskDTO != null) {
				amount.setValue(taskDTO.getAmount());
				if (taskDTO.getPrice() != null) {
					price.setValue(taskDTO.getPrice().getPrice());
					combo.setValue(new CurrencyModel(taskDTO.getPrice()
							.getCurrency()));
				}
			}

			simple.add(amount);
			simple.add(price);
			simple.add(combo);

			AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");
				}

				public void onSuccess(Set<CurrencyTypeDTO> result) {
					for (CurrencyTypeDTO c : result)
						listCurrencyModel.add(new CurrencyModel(c));
					if (taskDTO != null && taskDTO.getPrice() != null) {
						combo.setValue(new CurrencyModel(taskDTO.getPrice()
								.getCurrency()));
					}
				}
			};
			AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
					.create(AdministrationService.class);
			service.getCurrency(callback);
		}
		simple.add(addDeadlinePanel());
		addTranslationPanel(simple);
		vp1.add(simple);
	}

	protected void addTranslationPanel(FormPanel simple) {
		if (taskDTO != null && taskDTO.getTranslation() != null)
			translation = new TranslationPanel(new TranslationModel(
					taskDTO.getTranslation()));
		else
			translation = new TranslationPanel();
		simple.add(translation, new FormData(-10, 10));
	}

	private void getJobPanel() {
		AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");

			}

			@Override
			public void onSuccess(JobDTO result) {
				JobPanelProject jobPanel = new JobPanelProject(result,
						projectDTO);
				jobPanel.setAsMain();
			}
		};
		JobServiceAsync service = (JobServiceAsync) GWT
				.create(JobService.class);
		service.getJob(taskDTO.getJob().getId(), callback);
	}

	protected void addButtonPanel(VerticalPanel vp1) {
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		Button b = new Button("Update", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (amount.getValue() != null)
					taskDTO.setAmount(amount.getValue().intValue());
				if (price.getValue() != null && combo.getValue() != null)
					taskDTO.setPrice(new PriceDTO(price.getValue().intValue(),
							combo.getValue().getCurrencyDTO()));
				// taskDTO.setComment(comment.getValue());
				// taskDTO.setRating();
				taskDTO.setDescription(description.getDescription());
				taskDTO.setExpert((TranslatorDTO) workerPanel.getChosenWorker());
				taskDTO.setStatus(statusBar.getStatus());
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						Window.Location.assign("/spring_security_login");

					}

					@Override
					public void onSuccess(Void result) {
						getJobPanel();
					}
				};
				TaskServiceAsync service = (TaskServiceAsync) GWT
						.create(TaskService.class);
				service.update(taskDTO, callback);

			}
		});
		hp.add(b);
		Button b2 = new Button("Cancel", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				getJobPanel();
			}
		});
		hp.add(b2);
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Button b3 = new Button("Delete",
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							AsyncCallback<Void> callback = new AsyncCallback<Void>() {

								public void onFailure(Throwable t) {
									Window.Location
											.assign("/spring_security_login");
								}

								@Override
								public void onSuccess(Void result) {
									getJobPanel();
								}
							};
							TaskServiceAsync service = (TaskServiceAsync) GWT
									.create(TaskService.class);
							service.delete(taskDTO, callback);
						}
					});
			hp.add(b3);
		}
		vp1.add(hp);
	}

	private DateField addDeadlinePanel() {

		deadline = new DateField();
		deadline.setFieldLabel("Deadline");
		deadline.setData("text", "Enter your birthday");
		if (taskDTO != null)
			deadline.setValue(taskDTO.getDeadline());
		if (!AuthorizationManager.canChangeTask()) {
			deadline.setReadOnly(true);
		}
		return deadline;
	}

}
