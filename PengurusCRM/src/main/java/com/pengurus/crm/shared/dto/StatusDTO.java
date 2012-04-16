package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum StatusDTO implements IsSerializable {

	open(0), 
	in_progress(1), 
	resolved(2), 
	verificated(3), 
	accepted(4), 
	accounted(5),
	closed(6); 

	private final int stage;

	StatusDTO(int stage) {
		this.stage = stage;
	}

	public int toInt() {
		return stage;
	}

	public static String fromInt(int stage) {
		return StatusDTO.values()[stage].toString().replace('_', ' ');
	}

	public StatusDTO increase() {
		return StatusDTO.values()[this.toInt() + 1 >= 6 ? 6 : this.toInt() + 1];
	}

	public static StatusDTO getFirstStatus() {
		return open;
	}

	public StatusDTO decrease() {
		return StatusDTO.values()[this.toInt() - 1 <= 0 ? 0 : this.toInt() - 1];
	}
}
