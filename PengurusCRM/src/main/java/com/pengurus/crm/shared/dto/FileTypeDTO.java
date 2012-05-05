package com.pengurus.crm.shared.dto;

public enum FileTypeDTO {

	input (1),
	output (2);
	
	private final long type;

	FileTypeDTO(long type) {
		this.type = type;
	}

	public long type() {
		return type;
	}

}
