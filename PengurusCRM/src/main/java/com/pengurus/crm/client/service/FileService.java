package com.pengurus.crm.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("file.rpc")
public interface FileService extends RemoteService {
	
	List<String> getFileList(int quoteId, int jobId, int taskId, int stateId);
	void deleteFile(int quoteId, int jobId, int taskId, int stateId, String fileName);
	
}
