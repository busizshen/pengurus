package com.pengurus.crm.enums;

import com.pengurus.crm.shared.dto.StatusDTO;

public enum Status {

	open (0),
	closed (1), 
	in_progress (2), 
	resolved (3), 
	verificated (4), 
	accepted (5), 
	accounted (6);

	private final int stage;
	
	Status (int stage){
		this.stage = stage;
	}
	
	public int stage(){
		return stage;
	}
	
	public StatusDTO toDTO() {
		if (this == open)
			return StatusDTO.open;
		else if (this == closed)
			return StatusDTO.closed;
		else if (this == in_progress)
			return StatusDTO.in_progress;
		else if (this == resolved)
			return StatusDTO.resolved;
		else if (this == verificated)
			return StatusDTO.verificated;
		else if (this == accepted)
			return StatusDTO.accepted;
		else if (this == accounted)
			return StatusDTO.accounted;
		return null;
	}

	public static Status toStatus(StatusDTO status) {
		if (status == StatusDTO.open)
			return open;
		else if (status == StatusDTO.closed)
			return closed;
		else if (status == StatusDTO.in_progress)
			return in_progress;
		else if (status == StatusDTO.resolved)
			return resolved;
		else if (status == StatusDTO.verificated)
			return verificated;
		else if (status == StatusDTO.accepted)
			return accepted;
		else if (status == StatusDTO.accounted)
			return accounted;
		return null;
	}

}
