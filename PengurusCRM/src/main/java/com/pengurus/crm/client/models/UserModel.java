package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.UserDTO;

public class UserModel extends BaseModel {
	private static final long serialVersionUID = 8134206653269091514L;
	
	private UserDTO userDTO;

	protected UserModel() {}
	
	public UserModel(UserDTO userDTO) {
		setUserDTO(userDTO);
	}
	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
		if(userDTO.getId() != null)
			set("id",userDTO.getId());
		if(userDTO.getUsername() != null){
			set("username",userDTO.getUsername());
		}
		if(userDTO.getDescription() != null){
			set("description",userDTO.getDescription());
		}
		if(userDTO.getFullName() != null){
			set("fullName", userDTO.getFullName());
		}
	}

	public String getId(){
		return get("id");
	}
	
	public String getUsername(){
		return get("username");
	}
	
	public String getDescription(){
		return get("descritpion");
	}
	
	public String getFullName() {
		return get("fullName");
	}
	
	public Boolean compare(UserModel user){
		return this.userDTO.getId() == user.getUserDTO().getId();
	}
}
