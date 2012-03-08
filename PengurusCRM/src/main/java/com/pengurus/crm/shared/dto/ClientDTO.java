package com.pengurus.crm.shared.dto;

import java.util.Set;

public abstract class ClientDTO extends UserDTO {

	public ClientDTO() {
        super();
    }

    public ClientDTO(Long id, Set<UserRoleDTO> permission, String login, String password,
                  String description) {
        super(id, permission, login, password, description);
    }

	public abstract String getType();
 
    

}