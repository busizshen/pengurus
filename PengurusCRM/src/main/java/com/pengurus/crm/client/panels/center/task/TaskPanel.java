package com.pengurus.crm.client.panels.center.task;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
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
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByList;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByRoles;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class TaskPanel extends MainPanel {

	protected TaskDTO taskDTO;
	protected ProjectDTO projectDTO;
	protected NumberField amount;
	protected NumberField price;
	protected ComboBox<CurrencyModel> combo;
	protected DescriptionPanel description;
	protected TranslationPanel translation;
	protected DateField deadline;
	protected WorkerPanel workerPanel;
	protected WorkerPanel reviewerPanel;
	
	protected TaskPanel(TaskDTO taskDTO, ProjectDTO projectDTO){
		super();
		this.taskDTO = taskDTO;
		this.projectDTO = projectDTO;
	}
	protected WorkerPanel getReviewerPanel() {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
			roles.add(UserRoleDTO.ROLE_VERIFICATOR);
			reviewerPanel = new WorkerPanelEditByRoles(taskDTO.getReviewer(),
					"Reviewer", roles);
		} else {
			reviewerPanel = new WorkerPanelView(taskDTO.getReviewer(), "Reviewer");
		}
		return reviewerPanel;
	}
	
	protected WorkerPanel getTranslatorPanel() {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Set<WorkerDTO> translators = new HashSet<WorkerDTO>();
			translators.addAll(projectDTO.getExperts());
			workerPanel = new WorkerPanelEditByList(taskDTO.getExpert(),
					"Translator", translators);
		} else {
			workerPanel = new WorkerPanelView(taskDTO.getExpert(), "Translator");
		}
		return workerPanel;

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
	
	private void addTranslationPanel(FormPanel simple) {
		if (taskDTO != null && taskDTO.getTranslation() != null)
			translation = new TranslationPanel(new TranslationModel(
					taskDTO.getTranslation()));
		else
			translation = new TranslationPanel();
		simple.add(translation, new FormData(-10, 10));
	}

	protected void setStyle(HorizontalPanel hp) {
		hp.setAutoHeight(true);
		hp.setAutoWidth(true);
	}
	
}
