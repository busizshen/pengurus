package com.pengurus.crm.shared.dto;

public class StatusDTOCreater {
    	public StatusDTOCreater(){
    	}
    	
    	public StatusDTO getStatusDTO(String status){
    		if(status == "open" )
    			return StatusDTO.open;
    		else if(status == "canceled")
    			return StatusDTO.canceled;
    		else if(status == "inProgress")
    			return StatusDTO.inProgress;
    		else if(status == "resolved")
    			return StatusDTO.resolved;
    		else if(status =="verificated")
    			return StatusDTO.verificated;
    		else if(status == "acceppted")
    			return StatusDTO.acceppted;
    		else if(status == "accounted")
    			return StatusDTO.accounted;
    		return null;
    	}
}
