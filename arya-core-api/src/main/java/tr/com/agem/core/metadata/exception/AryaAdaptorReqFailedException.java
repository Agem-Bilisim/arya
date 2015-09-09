package tr.com.agem.core.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Adaptor failed to process request!")
public class AryaAdaptorReqFailedException extends RuntimeException {

	private static final long serialVersionUID = 4832314194301257858L;

}
