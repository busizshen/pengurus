package com.pengurus.crm.entities;

import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class UserUtils {

	public static User toUser(UserDTO userDTO) {
		User user = null;
		if (userDTO instanceof IndividualClientDTO) {
			user = new IndividualClient((IndividualClientDTO)userDTO);
		} else if(userDTO instanceof BusinessClientDTO) {
			user = new BusinessClient((BusinessClientDTO)userDTO);
		} else if(userDTO instanceof TranslatorDTO) {
			user = new Translator((TranslatorDTO)userDTO);
		} else if(userDTO instanceof WorkerDTO) {
			user = new Worker((WorkerDTO)userDTO);
		}
		return user;
	}
	
}
