package com.pengurus.entities;

import java.util.List;

import com.pengurus.enums.UserRole;

public abstract class Client extends User {

    public Client() {
        super();
    }

    public Client(List<UserRole> permission, String login, String password,
                  String description) {
        super(permission, login, password, description);
    }
 
    

}
