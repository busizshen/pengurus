package com.pengurus.crm.server.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pengurus.crm.enums.FileType;
import com.pengurus.crm.server.ExtendedPermissionEvaluator;
import com.pengurus.crm.server.FileUtils;
import com.pengurus.crm.server.services.UserServiceImpl;

@Controller
@RequestMapping("/file")
public class FileStreamController {

	protected static final Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private final String fileExp = "[a-zA-Z0-9._]+";
	
	private ExtendedPermissionEvaluator permissionEvaluator;
	
	public ExtendedPermissionEvaluator getPermissionEvaluator() {
		return permissionEvaluator;
	}

	public void setPermissionEvaluator(ExtendedPermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload/{quoteId}/{jobId}/{taskId}/{stateId}", method = RequestMethod.POST)
	protected void upload(
			@PathVariable Long quoteId, @PathVariable Long jobId,
			@PathVariable Long taskId, @PathVariable Long stateId,
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, com.pengurus.crm.client.service.exceptions.IOException {
		
		if (!permissionEvaluator.hasPermissionToFile(SecurityContextHolder
				.getContext().getAuthentication(), quoteId, jobId, taskId,
				FileType.valueOf(stateId), "upload")) {
			throw new AccessDeniedException("You don't have access to upload files from this folder.");
		}
		
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		
		File folder = new FileUtils().navigateInto(req.getSession().getServletContext(), quoteId, jobId, taskId, stateId);
		
		if (ServletFileUpload.isMultipartContent(req)) {

			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem item : items) {
					if (item.isFormField())
						continue;
					String fileName = item.getName();
					if (fileName != null) {
						fileName = FilenameUtils.getName(fileName);
					}
					fileName = fileName.replace(' ', '_');
					if (!fileName.matches(fileExp)) {
						throw new IOException(
								"File name can consist only of letters, numbers, '.' and '_' signs.");
					}

					File uploadedFile = new File(folder, fileName);
					if (uploadedFile.createNewFile()) {
						item.write(uploadedFile);
						resp.getOutputStream().print("The file was created successfully.");
					} else
						throw new IOException(
								"The file already exists in repository.");
				}
			} catch (Exception e) {
				resp.getOutputStream().print("An error occurred while creating the file : "
						+ e.getMessage());
				e.printStackTrace();
			}

		} else {
			resp.getOutputStream().print("Request contents type is not supported by the servlet.");
		}
	}

	private static final int BYTES_DOWNLOAD = 1024;
	
	@RequestMapping(value = "/download/{quoteId}/{jobId}/{taskId}/{stateId}/{fileName:" + fileExp + "}", method = RequestMethod.GET)
	public void download (
			@PathVariable Long quoteId, @PathVariable Long jobId,
			@PathVariable Long taskId, @PathVariable Long stateId,
			@PathVariable String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, com.pengurus.crm.client.service.exceptions.IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + fileName);
		
		if (!permissionEvaluator.hasPermissionToFile(SecurityContextHolder
				.getContext().getAuthentication(), quoteId, jobId, taskId,
				FileType.valueOf(stateId), "download")) {
			throw new AccessDeniedException("You don't have access to download files from this folder.");
		}
		
		log.error("Download! : " + fileName);
		
		ServletContext context = request.getSession().getServletContext();
		File folder = new FileUtils().navigateInto(context, quoteId, jobId, taskId, stateId);
		InputStream is = new FileInputStream(new File(folder, fileName));

		int read = 0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();

		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
	}

}
