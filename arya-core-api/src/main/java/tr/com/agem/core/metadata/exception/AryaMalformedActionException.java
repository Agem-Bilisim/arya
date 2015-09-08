package tr.com.agem.core.metadata.exception;

import java.net.MalformedURLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad action format!")
public class AryaMalformedActionException extends MalformedURLException {

	private static final long serialVersionUID = 4337044613229415453L;

}
