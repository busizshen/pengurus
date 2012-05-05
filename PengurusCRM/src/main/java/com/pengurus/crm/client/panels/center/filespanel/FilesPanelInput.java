package com.pengurus.crm.client.panels.center.filespanel;

import com.pengurus.crm.enums.FileType;

public class FilesPanelInput extends FilesPanel {

	public FilesPanelInput(Long quoteId, Long jobId, Long taskId,
			boolean canUpload, boolean canDelete) {
		super(quoteId, jobId, taskId, new Long(FileType.input.type()), canUpload, canDelete);
	}

}
