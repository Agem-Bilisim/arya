package tr.com.agem.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;


@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Login failed. Invalid username or password")
public class AryaLoginFailedException extends AryaException 
{
	private static final long serialVersionUID = -1854246638375264308L;
	
	public AryaLoginFailedException() {
		super();
	}
	
	public AryaLoginFailedException(AryaRequest request) {
		this(request, null);
	}

	public AryaLoginFailedException(AryaResponse response) {
		this(null, response);
	}

	public AryaLoginFailedException(String message) {
		super(null, null, message);
	}

	public AryaLoginFailedException(Exception exception) {
		super(null, null, exception);
	}

	public AryaLoginFailedException(AryaRequest request, AryaResponse response) {
		super(request, response, (String) null);
	}	
	
	public AryaLoginFailedException(AryaRequest request, AryaResponse response, String message) {
		super(request, response, message);
	}
	
	public AryaLoginFailedException(AryaRequest request, AryaResponse response, Exception exception) {
		super(request, response, exception);
	}
}
