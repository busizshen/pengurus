package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("number.rpc")
public interface NumberService extends RemoteService {
    public String getNumber();
}