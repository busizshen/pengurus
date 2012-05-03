package com.pengurus.crm.enums;

import com.pengurus.crm.shared.dto.StatusTaskDTO;

public enum StatusTask {
	open (0), 
	in_progress (1), 
	resolved (2), 
	verificated (3), 
	accepted (4), 
	accounted (5),
	closed(6);

	private final int stage;
	
	StatusTask (int stage){
		this.stage = stage;
	}
	
	public int stage(){
		return stage;
	}
	
	public StatusTaskDTO toDTO() {
		if (this == open)
			return StatusTaskDTO.open;
		else if (this == closed)
			return StatusTaskDTO.closed;
		else if (this == in_progress)
			return StatusTaskDTO.in_progress;
		else if (this == resolved)
			return StatusTaskDTO.resolved;
		else if (this == verificated)
			return StatusTaskDTO.verificated;
		else if (this == accepted)
			return StatusTaskDTO.accepted;
		else if (this == accounted)
			return StatusTaskDTO.accounted;
		return null;
	}

	public static StatusTask toStatus(StatusTaskDTO status) {
		if (status == StatusTaskDTO.open)
			return open;
		else if (status == StatusTaskDTO.closed)
			return closed;
		else if (status == StatusTaskDTO.in_progress)
			return in_progress;
		else if (status == StatusTaskDTO.resolved)
			return resolved;
		else if (status == StatusTaskDTO.verificated)
			return verificated;
		else if (status == StatusTaskDTO.accepted)
			return accepted;
		else if (status == StatusTaskDTO.accounted)
			return accounted;
		return null;
	}
}
