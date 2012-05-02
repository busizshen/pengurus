package com.pengurus.crm.server.servlets;

import java.io.File;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pengurus.crm.server.services.UserServiceImpl;

@Controller
@RequestMapping("/file")
public class FileStreamController {

	protected static final Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	protected void upload(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.error("JESTEMJESTEM!");

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

					File uploadedFile = new File(req
							.getSession()
							.getServletContext()
							.getRealPath(
									req.getSession().getServletContext()
											.getContextPath()), fileName);
					if (uploadedFile.createNewFile()) {
						item.write(uploadedFile);
						resp.setStatus(HttpServletResponse.SC_CREATED);
						resp.getWriter().print(
								"The file was created successfully.");
						resp.flushBuffer();
					} else
						throw new IOException(
								"The file already exists in repository.");
				}
			} catch (Exception e) {
				resp.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"An error occurred while creating the file : "
								+ e.getMessage());
				e.printStackTrace();
			}

		} else {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
					"Request contents type is not supported by the servlet.");
		}
	}

	private static final int BYTES_DOWNLOAD = 1024;
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition",
				"attachment;filename=downloadname.txt");
		ServletContext ctx = request.getSession().getServletContext();
		InputStream is = ctx.getResourceAsStream("/testing.txt");

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
