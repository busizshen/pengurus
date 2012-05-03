package com.pengurus.crm.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileServiceAsync {
	public void getFileList(int quoteId, int jobId, int taskId, int stateId, AsyncCallback<List<String>> callback);
	public void deleteFile(int quoteId, int jobId, int taskId, int stateId, String fileName, AsyncCallback<Void> callback);
}
