package com.pengurus.entities;

import java.util.List;

import com.pengurus.enums.UserRole;

public class Translator extends Worker{
    
    private List<Translation> translations;

    public Translator() {
        super();
    }

    public Translator(List<UserRole> permission, String login, String password,
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
