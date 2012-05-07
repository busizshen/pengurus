package com.pengurus.crm.client.panels.center.task;

import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelView;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.StatusTaskDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;

public class TaskPanelCreate extends TaskPanel {

	private Listener<DomEvent> listenerCreateTask;
	private Listener<DomEvent> listenerClose;
	private NumberField amount;
	private NumberField price;
	private JobDTO jobDTO;
	Button buttonCancel;
	Button buttonCreate;
	TasksListPanelEdit taskList;
	protected FormPanel mainPanel;

	public TaskPanelCreate(JobDTO jobDTO, ProjectDTO projectDTO,
			TasksListPanelEdit taskListPanel) {
		super(new TaskDTO(), projectDTO);
		setLayout(new FlowLayout());
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		mainPanel = new FormPanel();
		mainPanel.setFrame(false);
		mainPanel.setHeaderVisible(false);
		VerticalPanel vp1 = new VerticalPanel();
		vp1.setSpacing(10);
		vp1.add(getDeadlinePanel());
		addButtonPanel(vp1);
		addInfoPanel(vp1);
		hp.add(vp1);
		VerticalPanel vp2 = new VerticalPanel();
		vp2.setSpacing(10);
		addDescriptionPanel(vp2);
		hp.add(vp2);
		mainPanel.add(hp);
		mainPanel.add(getTranslatorPanel());
		mainPanel.add(getReviewerPanel());
		add(mainPanel);
		this.jobDTO = jobDTO;
		if (jobDTO.getTranslation() != null)
			translation.setTranslationValues(jobDTO.getTranslation(), 0, null);
		combo.setAllowBlank(false);
		amount.setAllowBlank(false);
		price.setAllowBlank(false);
		description.setAllowBlank(false);
		deadline.setAllowBlank(false);
		taskList = taskListPanel;

	}

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setListeners(Listener<DomEvent> listenerClose,
			Listener<DomEvent> listenerCreateTask) {
		this.listenerClose = listenerClose;
		if (buttonCancel != null)
			buttonCancel.addListener(Events.OnClick, listenerClose);
		this.listenerCreateTask = listenerCreateTask;
		if (buttonCreate != null)
			buttonCreate.addListener(Events.OnClick, listenerCreateTask);

	}

	protected void addButtonPanel(VerticalPanel vp1) {
		HorizontalPanel simple = new HorizontalPanel();
		setStyle(simple);
		buttonCreate = new Button("Create",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (checked()) {
							taskDTO.setTranslation(jobDTO.getTranslation());
							taskDTO.setJob(jobDTO);
							taskDTO.setStatus(StatusTaskDTO.open);
							taskDTO.setAmount(amount.getValue().intValue());
							taskDTO.setPrice(new PriceDTO(price.getValue()
									.intValue(), combo.getValue()
									.getCurrencyDTO()));
							taskDTO.setDeadline(deadline.getValue());
							taskDTO.setDescription(description.getDescription());
							taskDTO.setExpert((TranslatorDTO) workerPanel
									.getChosenWorker());
							taskDTO.setReviewer((TranslatorDTO) reviewerPanel
									.getChosenWorker());
							AsyncCallback<TaskDTO> callback = new AsyncCallback<TaskDTO>() {

								public void onFailure(Throwable t) {

								}

								@Override
								public void onSuccess(TaskDTO result) {
									jobDTO.getTask().add(result);
									taskList.refreshList(result);
								}
							};
							TaskServiceAsync service = (TaskServiceAsync) GWT
									.create(TaskService.class);
							service.createTask(taskDTO, callback);
						} else {
							MessageBox mb = new MessageBox();
							mb.setMessage("Please fill all fields");
							mb.show();
						}
					}

				});
		buttonCreate.addListener(Events.OnClick, listenerCreateTask);
		mainPanel.addButton(buttonCreate);
		buttonCancel = new Button("Cancel");
		buttonCancel.addListener(Events.OnClick, listenerClose);
		mainPanel.addButton(buttonCancel);

		mainPanel.setButtonAlign(HorizontalAlignment.CENTER);
	}

	protected boolean checked() {
		if (amount.getValue() == null)
			return false;
		if (price.getValue() == null)
			return false;
		if (combo.getValue() == null)
			return false;
		if (description.getDescription() == null)
			return false;
		if (deadline.getValue() == null)
			return false;
		return true;
	}

	protected void addStatusBar(VerticalPanel vp1) {
	}

	protected void addRatingPanel(VerticalPanel vp2) {
	}

	protected void addDescriptionPanel(VerticalPanel vp2) {
		description = new DescriptionPanelEdit("", 100, 300);
		vp2.add(description);
	}

	protected void addInfoPanel(VerticalPanel vp1) {
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
			amount.addListener(Events.OnChange, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					updateTranslation();

				}
			});

			price = new NumberField();
			price.setFieldLabel("Price");
			price.setName("price");
			price.setData("text", "Enter your price and choose Currnecy");
			price.addListener(Events.OnChange, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					updateTranslation();

				}
			});
			combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setData("text", "Choose Language");
			combo.setStore(listCurrencyModel);
			combo.addListener(Events.SelectionChange, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					updateTranslation();

				}
			});
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
		addTranslationPanel(simple);
		vp1.add(simple);
	}

	protected void updateTranslation() {
		Integer amountVal = 0;

		if (amount.getValue() != null)
			amountVal = amount.getValue().intValue();
		PriceDTO priceVal = null;
		if (combo.getValue() != null && price.getValue() != null)
			priceVal = new PriceDTO(price.getValue().intValue(), combo
					.getValue().getCurrencyDTO());
		translation.setTranslationValues(translation.getTranslation()
				.getTranslationDTO(), amountVal, priceVal);

	}

	protected void addTranslationPanel(FormPanel simple) {
		if (taskDTO != null && taskDTO.getTranslation() != null)
			translation = new TranslationPanelView(taskDTO.getTranslation(),
					taskDTO.getAmount(), taskDTO.getPrice());
		else
			translation = new TranslationPanelView();
		simple.add(translation, new FormData(-10, 10));
	}

}
