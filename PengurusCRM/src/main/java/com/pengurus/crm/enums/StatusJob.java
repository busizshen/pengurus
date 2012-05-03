package com.pengurus.crm.enums;
import com.pengurus.crm.shared.dto.StatusJobDTO;

public enum StatusJob {
	open (0), 
	in_progress (1), 
	resolved (2), 
	verificated (3), 
	accepted (4), 
	accounted (5),
	closed(6);

	private final int stage;
	
	StatusJob (int stage){
		this.stage = stage;
	}
	
	public int stage(){
		return stage;
	}
	
	public StatusJobDTO toDTO() {
		if (this == open)
			return StatusJobDTO.open;
		else if (this == closed)
			return StatusJobDTO.closed;
		else if (this == in_progress)
			return StatusJobDTO.in_progress;
		else if (this == resolved)
			return StatusJobDTO.resolved;
		else if (this == verificated)
			return StatusJobDTO.verificated;
		else if (this == accepted)
			return StatusJobDTO.accepted;
		else if (this == accounted)
			return StatusJobDTO.accounted;
		return null;
	}

	public static StatusJob toStatus(StatusJobDTO status) {
		if (status == StatusJobDTO.open)
			return open;
		else if (status == StatusJobDTO.closed)
			return closed;
		else if (status == StatusJobDTO.in_progress)
			return in_progress;
		else if (status == StatusJobDTO.resolved)
			return resolved;
		else if (status == StatusJobDTO.verificated)
			return verificated;
		else if (status == StatusJobDTO.accepted)
			return accepted;
		else if (status == StatusJobDTO.accounted)
			return accounted;
		return null;
	}
}
