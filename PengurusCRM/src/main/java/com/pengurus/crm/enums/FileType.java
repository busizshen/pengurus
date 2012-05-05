package com.pengurus.crm.enums;

public enum FileType {

	input (1),
	output (2);
	
	private final int type;

	FileType(int type) {
		this.type = type;
	}

	public int type() {
		return type;
	}
}
