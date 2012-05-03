package com.pengurus.crm.server.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.pengurus.crm.client.service.FileService;
import com.pengurus.crm.client.service.exceptions.FileNotFoundException;
import com.pengurus.crm.client.service.exceptions.IOException;
import com.pengurus.crm.server.FileUtils;

public class FileServiceImpl implements FileService {

	@Override
	public List<String> getFileList(int quoteId, int jobId, int taskId,
			int stateId) throws IOException {
		List<String> result = new ArrayList<String>();
		ServletContext context = RequestFactoryServlet.getThreadLocalServletContext();
		try {
			File folder = new FileUtils().navigateInto(context, quoteId, jobId, taskId, stateId);
			for (File file: folder.listFiles()) {
				result.add(file.getName());
			}
		} catch (java.io.IOException e) {
			throw new IOException();
		}
		return result;
	}

	@Override
	public void deleteFile(int quoteId, int jobId, int taskId, int stateId,
			String fileName) throws IOException {
		ServletContext context = RequestFactoryServlet.getThreadLocalServletContext();
		try {
			File folder = new FileUtils().navigateInto(context, quoteId, jobId, taskId, stateId);
			File file = new File(folder, fileName);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			if (!file.delete()) {
				throw new IOException();
			}
		} catch (java.io.IOException e) {
			throw new IOException();
		}
		return;
	}

}
