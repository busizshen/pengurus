package com.pengurus.crm.client.models;

import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientModel extends UserModel{
	private static final long serialVersionUID = 6928820266170700305L;
	
	public ClientModel(ClientDTO clientDTO){
		this.setClientDTO(clientDTO);
	}
	
	public ClientDTO getClientDTO() {
		return (ClientDTO) getUserDTO();
	}
	
	public void setClientDTO(ClientDTO clientDTO) {
		super.setUserDTO(clientDTO);
		/*if(clientDTO.getType() != null){
			set("type", clientDTO.getType());
		}*/
	}
	
	public String getType(){
		return get("type");
	}

}
