package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NumberServiceAsync {
    public void getNumber(AsyncCallback<String> callback);
}