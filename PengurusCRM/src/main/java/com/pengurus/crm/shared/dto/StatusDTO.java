package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum StatusDTO implements IsSerializable {

    open,
    canceled,
    inProgress,
    resolved,
    verificated,
    acceppted,
    accounted;
    
}
