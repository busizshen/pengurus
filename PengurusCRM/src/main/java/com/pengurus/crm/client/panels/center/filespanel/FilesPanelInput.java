package com.pengurus.crm.client.panels.center.filespanel;

import com.pengurus.crm.shared.dto.FileTypeDTO;

public class FilesPanelInput extends FilesPanel {

	public FilesPanelInput(Long quoteId, Long jobId, Long taskId,
			boolean canUpload, boolean canDelete) {
		super(quoteId, jobId, taskId, FileTypeDTO.input.type(), canUpload, canDelete, myConstants.FilesInput());
	}

}
