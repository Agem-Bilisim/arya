package tr.com.agem.arya.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.AryaIntercepterFactory;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.metadata.IMetadataEngine;
import tr.com.agem.core.metadata.exception.AryaMetadataNotFoundException;
import tr.com.agem.core.metadata.model.IMetadata;

@Controller
@RequestMapping("/rest")
public class AryaGateway {
	private static final Logger logger = Logger.getLogger(AryaGateway.class.getName());

	/**
	 * The requests from the client is redirected to the application adaptors
	 * where the request is mapped and converted as a service request.
	 */
	@Autowired
	AryaApplicationAdaptor applicationAdaptor;

	@Autowired
	IMetadataEngine metadataEngine;
	
	@Autowired
	AryaIntercepterFactory requestIntercepterFactory;

	@Autowired
	AryaIntercepterFactory responseIntercepterFactory;

	/**
	 * All requests are handled in this method.
	 * 
	 * @param applicationName
	 * @param aryaRequest
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{applicationName}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleRequest(@PathVariable("applicationName") String applicationName,
			@RequestBody AryaRequest aryaRequest, HttpServletRequest request, HttpServletResponse response) {
		
		IMetadata metadata = null;
		AryaResponse resp = new AryaResponse();
		
		//apply request intercepters if any
		if (requestIntercepterFactory != null && requestIntercepterFactory.getIntercepters() != null) {
			for (int i=0; i < requestIntercepterFactory.getIntercepters().length; i++) {
				resp= requestIntercepterFactory.getIntercepters()[i].intercept(applicationName, null, resp, request, response);
			}
		}
		
		logger.log(Level.INFO, "Application: {0}", applicationName);
		logger.log(Level.INFO, "AryaRequest: {0}", aryaRequest.toJSON());

		// if request asks only for the view, return <view> and <script> metadata
		if (RequestTypes.VIEW_ONLY.equals(aryaRequest.getRequestType())) {
			try {
				metadata = metadataEngine.findWithNameAsXML(applicationName, aryaRequest.getAction());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.toString(), e);
				throw new AryaMetadataNotFoundException();
			} 
		} else {

			IAryaAdaptorResponse adaptorResponse = applicationAdaptor.handleRequest(aryaRequest);

			// response includes view and interpreter asks for the view, prepare
			// the view for response
			if (adaptorResponse != null) {
				if (adaptorResponse.getViewName() != null && RequestTypes.ALL.equals(aryaRequest.getRequestType())) {
					metadata = metadataEngine.findWithNameAsXML(applicationName, adaptorResponse.getViewName());
				}
				resp.setData(adaptorResponse.getData());
			} else {
				if (RequestTypes.NAVBAR.equals(aryaRequest.getRequestType())) {
					metadata = metadataEngine.findWithNameAsXML(applicationName, aryaRequest.getAction());
				}
			}
		}

		if (metadata != null) {
			resp.setView(metadata.getMetadata());
		}
		
		//apply response intercepters if any			
		if (responseIntercepterFactory != null && responseIntercepterFactory.getIntercepters() != null) {
			for (int i=0; i < responseIntercepterFactory.getIntercepters().length; i++) {
				resp = responseIntercepterFactory.getIntercepters()[i].intercept(applicationName, metadata, resp, request, response);
			}
		}

		String respStr = resp.toString();

		logger.log(Level.INFO, "AryaResponse: {0}", respStr);
		
		return respStr;
	}
	
	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletResponse response, Exception e)
			throws Exception {
		
		logger.log(Level.SEVERE, e.getMessage(), e);
		
		// If the exception is annotated with @ResponseStatus, rethrow it and
		// let the framework handle it
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;
		
		// Otherwise setup and send the interpreter an exception message.
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage());
	}

}
