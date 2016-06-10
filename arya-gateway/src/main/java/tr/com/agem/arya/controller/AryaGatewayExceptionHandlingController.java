package tr.com.agem.arya.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.agem.core.exception.AryaException;
import tr.com.agem.core.gateway.model.AryaResponse;

@Controller
public class AryaGatewayExceptionHandlingController {
	
	private static final Logger logger = Logger.getLogger(AryaGatewayExceptionHandlingController.class.getName());
	
	@ExceptionHandler(value = AryaException.class)
	public String defaultErrorHandler(AryaException e)
			throws Exception {
		
		logger.log(Level.SEVERE, e.getMessage(), e);
		
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;
		
		
		AryaResponse response = new AryaResponse();
		response.setError(true);
		response.setMessage(e.getMessage());
		
		return response.toString();
	}

}
