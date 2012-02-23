package com.pengurus.crm.entities;

import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.shared.dto.ClientDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;


public abstract class Client extends User implements IsSerializable {

	public Client() {
        super();
    }

    public Client(Set<UserRoleDTO> permission, String login, String password,
                  String description) {
        super(permission, login, password, description);
    }
    
    public Client(ClientDTO clientDTO) {
    	super();
        String description;
		String password;
		String login;
		Set<UserRoleDTO> permission;
    }

	public abstract ClientDTO toDTO();

 
    

}
