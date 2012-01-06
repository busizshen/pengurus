package com.pengurus.crm.server;

import java.io.Serializable;

public class Kupa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Kupa(int id) {
		super();
		this.id = id;
	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
