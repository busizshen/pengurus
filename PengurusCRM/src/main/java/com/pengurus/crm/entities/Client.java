package com.pengurus.crm.entities;

import java.util.Set;
import com.google.gwt.user.client.rpc.IsSerializable;


public abstract class Client extends User implements IsSerializable {

	public Client() {
        super();
    }

    public Client(Set<UserRole> permission, String login, String password,
                  String description) {
        super(permission, login, password, description);
    }
 
    

}
