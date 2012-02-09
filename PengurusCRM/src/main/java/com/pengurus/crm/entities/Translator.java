package com.pengurus.crm.entities;

import java.util.List;
import java.util.Set;

import com.pengurus.crm.enums.UserRole;

public class Translator extends Worker{

	private static final long serialVersionUID = -3444544579987402428L;

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
