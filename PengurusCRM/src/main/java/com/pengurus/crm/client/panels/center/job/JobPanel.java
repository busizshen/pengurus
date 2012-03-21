package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.status.JobStatusPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public abstract class JobPanel extends MainPanel {

	TextField<String> deadline;
	DescriptionPanel description;
	JobStatusPanel status;
	TranslationPanel translation;
	protected JobDTO jobDTO;

	public JobPanel(JobDTO jobDTO) {
		super();
		this.jobDTO = jobDTO;
		addInfoPanel();
		addTranslationPanel();
	}

	private void addTranslationPanel() {
		if(jobDTO.getTranslation() != null)
			translation = new TranslationPanel(new TranslationModel(jobDTO.getTranslation()));
		else translation = new TranslationPanel();
		translation.setAnimCollapse(true);
		translation.setCollapsible(true);
		translation.collapse();
		add(translation);
		if (AuthorizationManager.canEditJob()) {
			AsyncCallback<Set<TranslationDTO>> callback = new AsyncCallback<Set<TranslationDTO>>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");
					
				}

				public void onSuccess(Set<TranslationDTO> result) {
					ListStore<TranslationModel> listTranslationModel = new ListStore<TranslationModel>();
					for (TranslationDTO c : result)
						listTranslationModel.add(new TranslationModel(c));
					translation.addEditPanel(listTranslationModel);
					translation.layout();
				}
			};
		 AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
					.create(AdministrationService.class);
			service.getTranslations(callback);
			
		}
	}

	private void addInfoPanel() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);
		hp.setBorders(true);
		hp.setAutoWidth(true);

		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		if(AuthorizationManager.canEditJobProject())
			vp.add(buttonPanel());
		
		status = new JobStatusPanel();
		vp.add(status);

		vp.add(addDeadlinePanel());

		hp.add(vp);
		description = new DescriptionPanel(jobDTO.getDescription());
		hp.add(description);

		add(hp);
	}

	private HorizontalPanel buttonPanel() {
		HorizontalPanel hp = new HorizontalPanel();
		Button b = new Button("Update");
		hp.add(b);
		
		if(AuthorizationManager.canEditJob()){
			Button b2 = new Button("Delete");
			hp.add(b2);
		}
		return hp;
	}

	private FormPanel addDeadlinePanel() {

		FormPanel hpDeadline = new FormPanel();
		hpDeadline.setHeaderVisible(false);
		hpDeadline.setBorders(true);
		final DateField deadline = new DateField();
		deadline.setFieldLabel("Deadline");
		deadline.setData("text", "Enter your birthday");
		deadline.setValue(jobDTO.getDeadline());
		deadline.setReadOnly(true);

		if (!AuthorizationManager.canEditJob()) {
			deadline.setReadOnly(true);
		}
		hpDeadline.add(deadline);

		return hpDeadline;
	}

}
