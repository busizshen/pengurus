package com.pengurus.crm.client.panels.center.quote;

import com.pengurus.crm.shared.dto.UserDTO;

public abstract class QuotesListPanelByUser extends QuotesListPanel {

	protected UserDTO userDTO;

	public QuotesListPanelByUser(UserDTO userDTO) {
		initPagination();
		this.userDTO = userDTO;
		modelList = new ModelList();
		add(modelList);
	}

	public QuotesListPanelByUser(UserDTO userDTO, int height, int width) {
		initPagination();
		this.userDTO = userDTO;
		modelList = new ModelList(height, width);
		add(modelList);
	}

	@Override
	protected String getName() {
		return userDTO.getFullName() + " quotes list";
	}

}
