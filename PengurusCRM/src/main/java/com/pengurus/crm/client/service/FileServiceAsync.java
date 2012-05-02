package com.pengurus.crm.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileServiceAsync {
	public void getFileList(AsyncCallback<List<String>> callback, int quoteId, int jobId, int taskId, int stateId);
	public void deleteFile(AsyncCallback<Void> callback, int quoteId, int jobId, int taskId, int stateId, String fileName);
}
