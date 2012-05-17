package com.pengurus.crm.client.panels.center.task;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByList;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByRoles;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class TaskPanel extends MainPanel {

	protected TaskDTO taskDTO;
	protected ProjectDTO projectDTO;
	protected ComboBox<CurrencyModel> combo;
	protected DescriptionPanel description;
	protected TranslationPanel translation;
	protected DateField deadline;
	protected WorkerPanel workerPanel;
	protected WorkerPanel reviewerPanel;
	protected ContentPanel mainBox;
	
	protected TaskPanel(TaskDTO taskDTO, ProjectDTO projectDTO){
		super();
		setHeading(myConstants.Task());
		this.taskDTO = taskDTO;
		this.projectDTO = projectDTO;
	}
	protected WorkerPanel getReviewerPanel() {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
			roles.add(UserRoleDTO.ROLE_VERIFICATOR);
			reviewerPanel = new WorkerPanelEditByRoles(taskDTO.getReviewer(),
					myConstants.Reviewer(), roles);
		} else {
			reviewerPanel = new WorkerPanelView(taskDTO.getReviewer(), myConstants.Reviewer());
		}
		return reviewerPanel;
	}
	
	protected WorkerPanel getTranslatorPanel() {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Set<WorkerDTO> translators = new HashSet<WorkerDTO>();
			translators.addAll(projectDTO.getExperts());
			workerPanel = new WorkerPanelEditByList(taskDTO.getExpert(),
					myConstants.Translator(), translators);
		} else {
			workerPanel = new WorkerPanelView(taskDTO.getExpert(), myConstants.Translator());
		}
		return workerPanel;

	}
	
	protected void setStyle(HorizontalPanel hp) {
		hp.setAutoHeight(true);
		hp.setAutoWidth(true);
	}
	
	protected FieldSet getDeadlinePanel() {

		FieldSet deadlinePanel = new FieldSet();
		deadlinePanel.setHeading(myConstants.Deadline());
		deadline = new DateField();
		deadline.setData("text", myConstants.EnterDeadline());
		if (taskDTO != null)
			deadline.setValue(taskDTO.getDeadline());
		deadlinePanel.add(deadline);
		return deadlinePanel;
	}

}
