package tr.com.agem.core.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;

public class AryaException extends RuntimeException 
{
	
	private static final long serialVersionUID = -9196936830735230804L;
	
	
	private AryaRequest request;
	private AryaResponse response;
	
	public AryaException() {
		this(null, null, (String) null);
	}
	
	@Override
	public String getMessage() {
		String msg = super.getMessage();
		if (msg == null) {
			msg = AnnotationUtils.findAnnotation(this.getClass(), ResponseStatus.class).reason();
		}
		return msg;
	}


	public AryaException(AryaRequest request) {
		this(request, null);
	}

	public AryaException(AryaResponse response) {
		this(null, response);
	}

	public AryaException(String message) {
		super(message);
	}

	public AryaException(Exception exception) {
		super(exception);
	}

	public AryaException(AryaRequest request, AryaResponse response) {
		this(request, response, (String) null);
	}
	
	public AryaException(AryaRequest request, AryaResponse response, String message) {
		super(message);
		this.request = request;
		this.response = response;
	}

	public AryaException(AryaRequest request, AryaResponse response, Exception exception) {
		super(exception);
		this.request = request;
		this.response = response;
	}

	public AryaRequest getRequest() {
		return request;
	}

	public void setRequest(AryaRequest request) {
		this.request = request;
	}

	public AryaResponse getResponse() {
		return response;
	}

	public void setResponse(AryaResponse response) {
		this.response = response;
	}

	
	
}
