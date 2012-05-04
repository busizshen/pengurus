package com.pengurus.crm.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.service.exceptions.IOException;

@RemoteServiceRelativePath("file.rpc")
public interface FileService extends RemoteService {
	
	public List<String> getFileList(Long quoteId, Long jobId, Long taskId, Long stateId) throws IOException;
	public void deleteFile(Long quoteId, Long jobId, Long taskId, Long stateId, String fileName) throws IOException;
	
}
