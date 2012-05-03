package com.pengurus.crm.server;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

public class FileUtils {

	public File navigateInto(File folder, String subfolder) throws IOException {
		File result = new File(folder, subfolder);
		if (!result.exists()) {
			if (!result.mkdir()) {
				throw new IOException();
			}
		}
		return result;
	}
	
	public File getRootFolder(ServletContext context) throws IOException {
		return navigateInto(new File(context.getRealPath("/")), "fileHierarchy");
	}
	
	public File navigateInto(ServletContext context, int quoteId, int jobId, int taskId, int stateId) throws IOException {
		File result = getRootFolder(context);
		if (quoteId > 0) {
			result = navigateInto(result, "quote_" + quoteId);
			if (jobId > 0) {
				result = navigateInto(result, "job_" + jobId);
				if (taskId > 0) {
					result = navigateInto(result,  "task_" + taskId);
					if (stateId > 0) {
						result = navigateInto(result, "state_" + stateId);
					}
				}
			}
		}
		return result;
	}
	
}
