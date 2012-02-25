package com.pengurus.crm.enums;

import com.pengurus.crm.shared.dto.StatusDTO;

public enum Status {

	open, closed, inProgress, resolved, verificated, accepted, accounted;

	public StatusDTO toDTO() {
		if (this == open)
			return StatusDTO.open;
		else if (this == closed)
			return StatusDTO.closed;
		else if (this == inProgress)
			return StatusDTO.inProgress;
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
		else if (status == StatusDTO.inProgress)
			return inProgress;
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
