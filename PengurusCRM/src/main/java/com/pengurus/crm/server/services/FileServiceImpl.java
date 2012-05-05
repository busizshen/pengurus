package com.pengurus.crm.server.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.web.context.ServletContextAware;

import com.pengurus.crm.client.service.FileService;
import com.pengurus.crm.client.service.exceptions.FileNotFoundException;
import com.pengurus.crm.client.service.exceptions.IOException;
import com.pengurus.crm.server.FileUtils;

public class FileServiceImpl implements FileService, ServletContextAware {

	private ServletContext servletContext;
	private PermissionEvaluator permissionEvaluator;
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public PermissionEvaluator getPermissionEvaluator() {
		return permissionEvaluator;
	}

	public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	@Override
	public List<String> getFileList(Long quoteId, Long jobId, Long taskId,
			Long stateId) throws IOException {
		List<String> result = new ArrayList<String>();
		try {
			File folder = new FileUtils().navigateInto(servletContext, quoteId, jobId, taskId, stateId);
			for (File file: folder.listFiles()) {
				result.add(file.getName());
			}
		} catch (java.io.IOException e) {
			throw new IOException();
		}
		return result;
	}

	@Override
	public void deleteFile(Long quoteId, Long jobId, Long taskId, Long stateId,
			String fileName) throws IOException {
		try {
			File folder = new FileUtils().navigateInto(servletContext, quoteId, jobId, taskId, stateId);
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
