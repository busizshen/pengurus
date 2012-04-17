package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.CommentDTO;

public class CommentModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3789185830519465288L;
	private CommentDTO commentDTO;

	public CommentDTO getCommentDTO() {
		return commentDTO;
	}

	public CommentModel(CommentDTO commentDTO){
		this.setCommentDTO(commentDTO);
	}

	public void setCommentDTO(CommentDTO commentDTO) {
		this.commentDTO = commentDTO;
		if(commentDTO != null)
			set("comment", this.commentDTO.getComment());
		if(commentDTO != null)
			set("author", this.commentDTO.getUser().getFullName());
	}
}
