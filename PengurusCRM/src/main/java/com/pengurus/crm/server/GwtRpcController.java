package com.pengurus.crm.server;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtRpcController extends RemoteServiceServlet implements
		Controller, ServletContextAware {
	private static final long serialVersionUID = -3618261744853211777L;
	
	transient private Logger log = LoggerFactory.getLogger("GwtRpcController");

	private ServletContext servletContext;
	private RemoteService remoteService;

	private Class<? extends RemoteService> remoteServiceClass;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		return null;
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		try {

			RPCRequest rpcRequest = RPC.decodeRequest(payload,
					this.remoteServiceClass);

			// delegate work to the spring injected service
			return RPC.invokeAndEncodeResponse(this.remoteService,
					rpcRequest.getMethod(), rpcRequest.getParameters());
		} catch (IncompatibleRemoteServiceException ex) {
			getServletContext()
					.log("An IncompatibleRemoteServiceException was thrown while processing this call.",
							ex);
			return RPC.encodeResponseForFailure(null, ex);
		}
	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
		this.remoteServiceClass = this.remoteService.getClass();
	}
	
	@Override
	protected void doUnexpectedFailure(Throwable e) {
		log.error("=====> ERROR ");
		StringWriter traceWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(traceWriter, false);
		e.printStackTrace(printWriter);
		printWriter.close();
		log.error(traceWriter.getBuffer().toString());
		log.error("<===== ERROR ");
		super.doUnexpectedFailure(e);
	}

}