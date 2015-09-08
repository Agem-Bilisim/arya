package tr.com.agem.core.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Metadata not found!")
public class AryaMetadataNotFoundException extends NullPointerException {

	private static final long serialVersionUID = 3551284101068395557L;

}
