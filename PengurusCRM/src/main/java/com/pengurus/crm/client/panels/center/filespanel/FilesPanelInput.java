package com.pengurus.crm.client.panels.center.filespanel;

public class FilesPanelInput extends FilesPanel {

	public FilesPanelInput(Long quoteId, Long jobId, Long taskId,
			boolean canUpload, boolean canDelete) {
		super(quoteId, jobId, taskId, new Long(1), canUpload, canDelete);
	}

}
