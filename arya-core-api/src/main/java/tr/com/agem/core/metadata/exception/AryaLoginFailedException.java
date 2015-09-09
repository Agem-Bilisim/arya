package tr.com.agem.core.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Login failed!")
public class AryaLoginFailedException extends RuntimeException {

	private static final long serialVersionUID = -1854246638375264308L;

}
