package com.pengurus.crm.client.panels.center.filespanel;

import com.pengurus.crm.enums.FileType;

public class FilesPanelOutput extends FilesPanel{

	public FilesPanelOutput(Long quoteId, Long jobId, Long taskId,
			boolean canUpload, boolean canDelete) {
		super(quoteId, jobId, taskId, new Long(FileType.output.type()), canUpload, canDelete);
	}

}
