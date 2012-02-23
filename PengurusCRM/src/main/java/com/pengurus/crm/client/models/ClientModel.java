package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientModel extends BaseModel{

	/**TO DO
	 * trzeba dodać nazwę klienta nie tylko ten nieszczęsny login - jak login można nazwać username ?
	 * 
	 */
	private static final long serialVersionUID = -328107319249539300L;
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
	
	public String getId(){
		return get("id");
	}
	
	public String getUsername(){
		return get("username");
	}
	
	public String getType(){
		return get("type");
	}
	
	public String getDescription(){
		return get("descritpion");
	}

}
