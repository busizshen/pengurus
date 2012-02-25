package com.pengurus.crm.enums;

import com.pengurus.crm.shared.dto.RatingDTO;

public enum Rating {
    
    veryBad,
    bad,
    ok,
    good,
    veryGood;

	public RatingDTO toDTO() {
		if(this == veryBad)
			return RatingDTO.veryBad;
		if(this == bad)
			return RatingDTO.bad;
		if(this == ok)
			return RatingDTO.ok;
		if(this == good)
			return RatingDTO.good;
		if(this == veryGood)
			return RatingDTO.veryGood;
		return null;
	}    
}
