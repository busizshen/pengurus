package com.pengurus.crm.enums;

import com.pengurus.crm.shared.dto.StatusQuoteDTO;

public enum StatusQuote {

	open (0), 
	preparing (1), 
	ready (2), 
	agreed (3), 
	in_progress (4), 
	accepted (5),
	accounted (6),
	closed (7);

	private final int stage;
	
	StatusQuote (int stage){
		this.stage = stage;
	}
	
	public int stage(){
		return stage;
	}
	
	public StatusQuoteDTO toDTO() {
		if (this == open)
			return StatusQuoteDTO.open;
		else if (this == closed)
			return StatusQuoteDTO.closed;
		else if (this == in_progress)
			return StatusQuoteDTO.in_progress;
		else if (this == ready)
			return StatusQuoteDTO.ready;
		else if (this == agreed)
			return StatusQuoteDTO.agreed;
		else if (this == accepted)
			return StatusQuoteDTO.accepted;
		else if (this == accounted)
			return StatusQuoteDTO.accounted;
		else if (this == preparing)
			return StatusQuoteDTO.preparing;
		return null;
	}

	public static StatusQuote toStatus(StatusQuoteDTO status) {
		if (status == StatusQuoteDTO.open)
			return open;
		else if (status == StatusQuoteDTO.closed)
			return closed;
		else if (status == StatusQuoteDTO.in_progress)
			return in_progress;
		else if (status == StatusQuoteDTO.ready)
			return ready;
		else if (status == StatusQuoteDTO.agreed)
			return agreed;
		else if (status == StatusQuoteDTO.accepted)
			return accepted;
		else if (status == StatusQuoteDTO.accounted)
			return accounted;
		else if (status == StatusQuoteDTO.preparing)
			return preparing;
		return null;
	}

}
