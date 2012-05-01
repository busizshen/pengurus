package com.pengurus.crm.client.panels.center.rating;

import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.shared.dto.TaskDTO;

public class CommentPanel extends FieldSet {

	protected TextArea comment;
	
	public CommentPanel(TaskDTO taskDTO){
		setHeading("Comment");
		comment = new TextArea();
		comment.setWidth(300);
		comment.setHeight(100);
		comment.setValue(taskDTO.getComment());
		if(!AuthorizationManager.hasVerificatorAccess(taskDTO)){
			comment.setReadOnly(true);
		}
		add(comment);
	}
	
	public String getComment(){
		return comment.getValue();
	}
}
