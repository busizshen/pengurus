package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum StatusJobDTO implements IsSerializable{

	open(0), 
	in_progress(1), 
	resolved(2), 
	verificated(3), 
	accepted(4), 
	accounted(5),
	closed(6); 

	private final int stage;

	StatusJobDTO(int stage) {
		this.stage = stage;
	}

	public int toInt() {
		return stage;
	}

	public static String fromInt(int stage) {
		return StatusJobDTO.values()[stage].toString().replace('_', ' ');
	}

	public StatusJobDTO increase() {
		return StatusJobDTO.values()[this.toInt() + 1 >= 6 ? 6 : this.toInt() + 1];
	}

	public static StatusJobDTO getFirstStatus() {
		return open;
	}

	public StatusJobDTO decrease() {
		return StatusJobDTO.values()[this.toInt() - 1 <= 0 ? 0 : this.toInt() - 1];
	}
}
