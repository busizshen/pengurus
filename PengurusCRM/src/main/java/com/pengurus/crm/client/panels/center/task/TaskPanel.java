package com.pengurus.crm.client.panels.center.task;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.RatingPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.status.TaskStatusPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskPanel extends MainPanel {

	protected TaskDTO taskDTO;
	protected ProjectDTO projectDTO;
	protected NumberField amount;
	protected DescriptionPanel description;
	protected TranslationPanel translation;
	TaskStatusPanel statusBar;
	RatingPanel rating;
	
	public TaskPanel(TaskDTO task, ProjectDTO jobDTO) {
		this.taskDTO = task;
		this.projectDTO = jobDTO;
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		hp.setBorders(true);
		VerticalPanel vp1 = new VerticalPanel();
		vp1.setSpacing(5);
		addButtonPanel(vp1);
		addStatusBar(vp1);
		addInfoPanel(vp1);
		hp.add(vp1);
		VerticalPanel vp2 = new VerticalPanel();
		vp2.setSpacing(5);
		addRatingPanel(vp2);
		addDescriptionPanel(vp2);
		hp.add(vp2);
		vp.add(hp);
		addTranslatorPanel(vp);
		add(vp);
		
	}

	protected void setStyle(HorizontalPanel hp) {
		hp.setSpacing(5);
		hp.setAutoHeight(true);
		hp.setAutoWidth(true);
		hp.setBorders(true);
		
	}

	private void addTranslatorPanel(VerticalPanel vp) {
		// TODO Auto-generated method stub
		
	}

	protected void addStatusBar(VerticalPanel vp1) {
		statusBar= new TaskStatusPanel();
		vp1.add(statusBar);
	}

	private void addDescriptionPanel(VerticalPanel vp2) {
		description = new DescriptionPanelEdit("");
		vp2.add(description);
	}

	protected void addRatingPanel(VerticalPanel vp2) {
		rating = new RatingPanel();
		vp2.add(rating);
	}

	private void addInfoPanel(VerticalPanel vp1) {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setBorders(true);
		simple.setAutoHeight(true);
		simple.setAutoWidth(true);
		simple.setHeaderVisible(false);
		if(AuthorizationManager.canEditProject(projectDTO)){

			final ListStore<CurrencyModel> listCurrencyModel =new  ListStore<CurrencyModel>();
			final NumberField amount = new NumberField();
			amount.setFieldLabel("Price");
			amount.setData("text", "Enter your amount and choose Currnecy");

			final ComboBox<CurrencyModel> combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setData("text", "Choose Language");
			combo.setStore(listCurrencyModel);
			
			if(taskDTO.getPrice() != null){
				amount.setValue(taskDTO.getPrice().getPrice());
				combo.setValue(new CurrencyModel(taskDTO.getPrice().getCurrency()));
			}
			
			simple.add(amount);
			simple.add(combo);
			
			AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");
				}

				public void onSuccess(Set<CurrencyTypeDTO> result) {
					for (CurrencyTypeDTO c : result)
						listCurrencyModel.add(new CurrencyModel(c));
					if(taskDTO.getPrice() != null){
						combo.setValue(new CurrencyModel(taskDTO.getPrice().getCurrency()));
					}
				}
			};
			AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
					.create(AdministrationService.class);
			service.getCurrency(callback);
		}
		addTranslationPanel(simple);
		simple.add(addDeadlinePanel());
		vp1.add(simple);
	}

	protected void addTranslationPanel(FormPanel simple) {
		if(taskDTO.getTranslation() != null)
			translation = new TranslationPanel(new TranslationModel(taskDTO.getTranslation()));
		else translation = new TranslationPanel();
		simple.add(translation);
	}

	protected void addButtonPanel(VerticalPanel vp1) {
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		Button b = new Button("Update");
		hp.add(b);
		Button b2 = new Button("Cancel");
		hp.add(b2);
		if(AuthorizationManager.canEditProject(projectDTO)){
			Button  b3 = new Button("Delete");
			hp.add(b3);
		}
		vp1.add(hp);
	}
	
	private FormPanel addDeadlinePanel() {

		FormPanel hpDeadline = new FormPanel();
		hpDeadline.setHeaderVisible(false);
		hpDeadline.setBorders(true);
		final DateField deadline = new DateField();
		deadline.setFieldLabel("Deadline");
		deadline.setData("text", "Enter your birthday");
		deadline.setValue(taskDTO.getDeadline());
		deadline.setReadOnly(true);

		if (!AuthorizationManager.canChangeTask()) {
			deadline.setReadOnly(true);
		}
		hpDeadline.add(deadline);

		return hpDeadline;
	}

}
