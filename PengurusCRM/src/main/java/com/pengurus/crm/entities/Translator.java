package com.pengurus.crm.entities;

import java.util.List;
import java.util.Set;


public class Translator extends Worker{

	private List<Translation> translations;

    public Translator() {
        super();
    }

    public Translator(Set<UserRole> permission, String login, String password,
                      String description, PersonalData data,
                      List<Translation> translations) {
        super(permission, login, password, description, data);
        this.translations = translations;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
    
}
