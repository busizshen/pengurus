package com.pengurus.crm.client.models;

import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientModel extends UserModel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6928820266170700305L;
	private ClientDTO clientDTO;
	public ClientModel(ClientDTO clientDTO){
		this.setClientDTO(clientDTO);
	}
	public ClientDTO getClientDTO() {
		return clientDTO;
	}
	public void setClientDTO(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
		if(clientDTO.getId() != null)
			set("id",clientDTO.getId());
		if(clientDTO.getUsername() != null){
			set("username",clientDTO.getUsername());
		}
		if(clientDTO.getDescription() != null){
			set("description",clientDTO.getDescription());
		}
		/*if(clientDTO.getType() != null){
			set("type", clientDTO.getType());
		}*/
	}
	
	public String getType(){
		return get("type");
	}

}
