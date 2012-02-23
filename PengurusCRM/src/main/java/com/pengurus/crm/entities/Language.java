package com.pengurus.crm.entities;

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
    
}
