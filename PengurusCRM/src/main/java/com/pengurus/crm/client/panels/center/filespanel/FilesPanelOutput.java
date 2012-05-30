package com.pengurus.crm.client.panels.center.filespanel;

import com.pengurus.crm.shared.dto.FileTypeDTO;

public class FilesPanelOutput extends FilesPanel{

	public FilesPanelOutput(Long quoteId, Long jobId, Long taskId,
			boolean canUpload, boolean canDelete) {
		super(quoteId, jobId, taskId, FileTypeDTO.output.type(), canUpload, canDelete, myConstants.FilesOutput());
	}

}
