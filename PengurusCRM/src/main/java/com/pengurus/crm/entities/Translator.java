package com.pengurus.crm.entities;

import java.util.Set;


public class Translator extends Worker{

	private Set<Translation> translations;

    public Translator() {
        super();
    }

    public Translator(Set<UserRole> permission, String login, String password,
                      String description, PersonalData data,
                      Set<Translation> translations) {
        super(permission, login, password, description, data);
        this.translations = translations;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }
    
}
