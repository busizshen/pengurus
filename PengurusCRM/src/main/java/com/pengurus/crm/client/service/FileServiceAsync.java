package com.pengurus.crm.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileServiceAsync {
	public void getFileList(Long quoteId, Long jobId, Long taskId, Long stateId, AsyncCallback<List<String>> callback);
	public void deleteFile(Long quoteId, Long jobId, Long taskId, Long stateId, String fileName, AsyncCallback<Void> callback);
}
