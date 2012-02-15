package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LanguageDTO implements IsSerializable {

	   private String language;

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
	    
}
