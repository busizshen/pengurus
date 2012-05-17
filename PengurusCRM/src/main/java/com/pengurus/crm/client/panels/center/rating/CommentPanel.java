package com.pengurus.crm.client.panels.center.rating;

import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.core.client.GWT;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.i18nConstants;
import com.pengurus.crm.shared.dto.TaskDTO;

public class CommentPanel extends FieldSet {

	protected TextArea comment;
	private  i18nConstants myConstants = (i18nConstants)GWT.create(i18nConstants.class);
	
	public CommentPanel(TaskDTO taskDTO, Integer height ,Integer width){
		setHeading(myConstants.Comment());
		comment = new TextArea();
		comment.setWidth(width);
		comment.setHeight(height);
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
