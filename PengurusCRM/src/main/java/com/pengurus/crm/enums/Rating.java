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
			return RatingDTO.very_bad;
		if(this == bad)
			return RatingDTO.bad;
		if(this == ok)
			return RatingDTO.ok;
		if(this == good)
			return RatingDTO.good;
		if(this == veryGood)
			return RatingDTO.very_good;
		return null;
	}    
	
	public static Rating toRating(RatingDTO r){
		if(r == RatingDTO.very_bad)
			return Rating.veryBad;
		if(r == RatingDTO.bad)
			return Rating.bad;
		if(r == RatingDTO.ok)
			return Rating.ok;
		if(r == RatingDTO.good)
			return Rating.good;
		if(r == RatingDTO.very_good)
			return Rating.veryGood;
		return null;
	}
}
