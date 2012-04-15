package com.pengurus.crm.client.service.exceptions;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DeleteException extends Exception implements IsSerializable {

	private static final long serialVersionUID = -1269150152761215271L;

	@Override
	public String getMessage() {
		return "There are still references to that object";
	}
}
