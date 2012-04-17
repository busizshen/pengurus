package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CommentDTO implements IsSerializable {

	private String comment;
	private UserDTO user;

	public CommentDTO(String comment, UserDTO user ){
		super();
		this.setComment(comment);
		this.setUser(user);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public UserDTO getUser() {
		return user;
	}
}
