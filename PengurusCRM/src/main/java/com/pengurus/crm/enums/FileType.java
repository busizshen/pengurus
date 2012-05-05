package com.pengurus.crm.enums;

public enum FileType {

	input(1), output(2);

	private final long type;

	FileType(long type) {
		this.type = type;
	}

	public long type() {
		return type;
	}

	public static FileType valueOf(long type) {
		for (FileType fileType: values()) {
			if (fileType.type() == type) {
				return fileType;
			}
		}
		return null;
	}
}
