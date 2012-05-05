package com.pengurus.crm.server.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ServletContextAware;

import com.pengurus.crm.client.service.FileService;
import com.pengurus.crm.client.service.exceptions.FileNotFoundException;
import com.pengurus.crm.client.service.exceptions.IOException;
import com.pengurus.crm.enums.FileType;
import com.pengurus.crm.server.ExtendedPermissionEvaluator;
import com.pengurus.crm.server.FileUtils;

public class FileServiceImpl implements FileService, ServletContextAware {

	private ServletContext servletContext;
	private ExtendedPermissionEvaluator permissionEvaluator;

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ExtendedPermissionEvaluator getPermissionEvaluator() {
		return permissionEvaluator;
	}

	public void setPermissionEvaluator(
			ExtendedPermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	@Override
	public List<String> getFileList(Long quoteId, Long jobId, Long taskId,
			Long stateId) throws IOException {
		
		if (!permissionEvaluator.hasPermissionToFile(SecurityContextHolder
				.getContext().getAuthentication(), quoteId, jobId, taskId,
				FileType.valueOf(stateId), "download")) {
			throw new AccessDeniedException("You don't have access to download files from this folder.");
		}

		List<String> result = new ArrayList<String>();
		try {
			File folder = new FileUtils().navigateInto(servletContext, quoteId,
					jobId, taskId, stateId);
			for (File file : folder.listFiles()) {
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
		
		if (!permissionEvaluator.hasPermissionToFile(SecurityContextHolder
				.getContext().getAuthentication(), quoteId, jobId, taskId,
				FileType.valueOf(stateId), "delete")) {
			throw new AccessDeniedException("You don't have access to delete files from this folder.");
		}
		
		try {
			File folder = new FileUtils().navigateInto(servletContext, quoteId,
					jobId, taskId, stateId);
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
