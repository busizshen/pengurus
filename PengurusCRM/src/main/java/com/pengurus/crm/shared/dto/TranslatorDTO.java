package com.pengurus.crm.shared.dto;

import java.util.HashSet;
import java.util.Set;


public class TranslatorDTO extends WorkerDTO {

	private Set<TranslationDTO> translations = new HashSet<TranslationDTO>();

    public TranslatorDTO() {
        super();
    }

    public TranslatorDTO(Long id, Set<UserRoleDTO> permission, String login, String password,
                      String description, PersonalDataDTO data,
                      Set<TranslationDTO> translations) {
        super(id, permission, login, password, description, data);
        this.translations = translations;
    }

    public Set<TranslationDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<TranslationDTO> translations) {
        this.translations = translations;
    }
    
}
