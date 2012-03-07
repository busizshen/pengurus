package com.pengurus.crm.entities;

import com.pengurus.crm.shared.dto.LanguageDTO;

public class Language {
    
    private Long Id;
    private String language;

    public Language() {
        super();
    }
    
    public Language(String language) {
        super();
        this.language = language;
    }
    
    public Language(LanguageDTO lang) {
		super();
		if(lang != null){
			this.language = lang.getLanguage();
		}
	}

	public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public LanguageDTO toDTO(){
    	LanguageDTO lDTO = new LanguageDTO();
    	lDTO.setLanguage(this.language);
    	lDTO.setId(this.Id);
    	return lDTO;
    }
    
}
