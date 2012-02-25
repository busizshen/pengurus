package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LanguageDTO implements IsSerializable {

	private String language;
	private Long id;

	public LanguageDTO() {
		super();
	}

	public LanguageDTO(String language) {
		super();
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
