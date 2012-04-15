package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum StatusDTO implements IsSerializable {

	open(0), 
	closed(1), 
	in_progress(2), 
	resolved(3), 
	verificated(4), 
	accepted(5), 
	accounted(6);

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
		return StatusDTO.values()[this.toInt() + 1 >= 6 ? 6 : this.toInt()];
	}

	public static StatusDTO getFirstStatus() {
		return open;
	}

	public StatusDTO decrease() {
		return StatusDTO.values()[this.toInt() - 1 <= 0 ? 0 : this.toInt()];
	}
}
