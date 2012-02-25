package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;


public class Translator extends Worker{

	private Set<Translation> translations;

    public Translator() {
        super();
    }

    public Translator(Set<UserRoleDTO> permission, String login, String password,
                      String description, PersonalData data,
                      Set<Translation> translations) {
        super(permission, login, password, description, data);
        this.translations = translations;
    }

    public Translator(TranslatorDTO userDTO) {
    	init(userDTO);
	}

	public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public TranslatorDTO toDTO(){
    	TranslatorDTO tDTO = this.toDTOLazy();
    	for(Translation t : this.getTranslations())
    		tDTO.getTranslations().add(t.toDTO());
    	return tDTO; 
    }
    
    @Override
    public TranslatorDTO toDTOLazy(){
    	TranslatorDTO tDTO = new TranslatorDTO();
    	tDTO.setDescription(this.getDescription());
    	tDTO.setId(this.getId());
    	if(this.getPersonalData() != null)
    		tDTO.setPersonalData(this.getPersonalData().toDTO());
    	tDTO.setUsername(this.getUsername());
    	for(UserRoleDTO a : this.getAuthorities()){
			tDTO.getAuthorities().add(a);
		}
    	return tDTO; 
    }
}
