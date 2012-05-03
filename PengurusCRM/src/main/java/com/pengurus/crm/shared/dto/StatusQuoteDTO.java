package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum StatusQuoteDTO implements IsSerializable {
	open (0), 
	preparing (1), 
	ready (2), 
	agreed (3), 
	in_progress (4), 
	accepted (5),
	accounted (6),
	closed (7);

	private final int stage;

	StatusQuoteDTO(int stage) {
		this.stage = stage;
	}

	public int toInt() {
		return stage;
	}

	public static String fromInt(int stage) {
		return StatusQuoteDTO.values()[stage].toString().replace('_', ' ');
	}

	public StatusQuoteDTO increase() {
		return StatusQuoteDTO.values()[this.toInt() + 1 >= 7 ? 7 : this.toInt() + 1];
	}

	public static StatusQuoteDTO getFirstStatus() {
		return open;
	}

	public StatusQuoteDTO decrease() {
		return StatusQuoteDTO.values()[this.toInt() - 1 <= 0 ? 0 : this.toInt() - 1];
	}
}
