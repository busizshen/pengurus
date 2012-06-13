package com.pengurus.crm.client.panels.center.rating;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CommentModel;
import com.pengurus.crm.shared.dto.CommentDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class RatingPanel extends FormPanel {

	TaskDTO taskDTO;
	ProjectDTO projectDTO;
	CommentListPanel comments;
	public RatingPanel(TaskDTO taskDTO, ProjectDTO projectDTO) {
		this.taskDTO = taskDTO;
		this.projectDTO = projectDTO;
		addRatingPanel();
		addCommentPanel();
		addCommentsListPanel();
	}

	private void addCommentsListPanel() {
		comments = new CommentListPanel();
		add(comments);
	}

	private void addCommentPanel() {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			FormPanel commentPanel = new FormPanel();
			final TextBox comment = new TextBox();
			commentPanel.add(comment);
			commentPanel.setHeaderVisible(false);
			commentPanel.setBorders(true);
			Button b = new Button("Add", new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					CommentDTO commentDTO = new CommentDTO(comment.getValue(), AuthorizationManager.getCurrentUser());
					comments.getGrid().stopEditing();
					comments.getStore().add(
							new CommentModel(commentDTO));
					comments.getGrid().startEditing(0, 0);
				}

			});
			commentPanel.addButton(b);
			add(commentPanel);
		}

	}

	private void addRatingPanel() {

	}
}
