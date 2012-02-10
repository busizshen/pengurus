package com.pengurus.crm.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.UserEntity;

public class CurrentSessionServiceImpl implements CurrentSessionService {

	private final Logger log = LoggerFactory.getLogger("Krowa");
	
	@Override
	public UserEntity getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		log.error("====> Zostalem wywolany");
		
		if (principal instanceof User) {
			log.error("====> wewnatrz");
			if (principal == null) {
				log.error("====> null");
			}
			log.error(((User)principal).toString());
			return new UserEntity(((User) principal).getUsername());
		}

		log.error("====> Zwracam nulla");
		
		return null;
	}

}
