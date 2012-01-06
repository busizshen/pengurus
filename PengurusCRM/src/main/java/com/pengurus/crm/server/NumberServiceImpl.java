package com.pengurus.crm.server;

import com.pengurus.crm.client.service.NumberService;

public class NumberServiceImpl implements NumberService {

	private int currentNumber = 1;
	
	@Override
	public String getNumber() {
		return String.valueOf(currentNumber++);
	}

}
