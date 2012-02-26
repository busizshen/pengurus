package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum StatusDTO implements IsSerializable {

    open,
    closed,
    inProgress,
    resolved,
    verificated,
    accepted,
    accounted;

	public int toInt() {
		if(this == open)
			return 1;
		else if (this == inProgress)
			return 2;
		else if (this == resolved)
			return 3;
		else if (this == verificated)
			return 4;
		else if (this == accepted)
			return 5;
		else if (this == accounted)
			return 6;
		else if (this == closed)
			return 7;
		return 0;
	}

	public StatusDTO increase() {
		if(this == open)
			return inProgress;
		else if (this == inProgress)
			return resolved;
		else if (this == resolved)
			return verificated;
		else if (this == verificated)
			return accepted;
		else if (this == accepted)
			return accounted;
		else if (this == accounted)
			return closed;
		return closed;
		
	}

	public static StatusDTO getFirstStatus() {
		return open;
	}

	public StatusDTO decrease() {
		if(this == open)
			return open;
		else if (this == inProgress)
			return open;
		else if (this == resolved)
			return inProgress;
		else if (this == verificated)
			return resolved;
		else if (this == accepted)
			return verificated;
		else if (this == accounted)
			return accepted;
		return open;
	}
    
}
