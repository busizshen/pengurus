package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum RatingDTO implements IsSerializable {
    
    very_bad (0),
    bad (1),
    ok (2),
    good (3),
    very_good (4);  
	
    private final int stage;

	RatingDTO(int stage) {
		this.stage = stage;
	}

	public int toInt() {
		return stage;
	}

	public static String fromInt(int stage) {
		return RatingDTO.values()[stage].toString().replace('_', ' ');
	}

	public RatingDTO increase() {
		return RatingDTO.values()[this.toInt() + 1 >= 4 ? 4 : this.toInt()];
	}

	public static RatingDTO getFirstRating() {
		return ok;
	}

	public RatingDTO decrease() {
		return RatingDTO.values()[this.toInt() - 1 <= 0 ? 0 : this.toInt()];
	}
}
