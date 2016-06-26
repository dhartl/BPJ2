package at.c02.bpj.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import at.c02.bpj.exception.ClientException;
import at.c02.bpj.server.bean.ServerError;

/**
 * <p>
 * Wenn ein Controller eine Exception wirft, behandelt diese Klasse diese
 * Exception.
 * </p>
 * <p>
 * Wenn die Exception von der Klasse {@link ClientException} ist, dann liefert
 * der Server dem Aufrufer eine Response mit dem Text-Inhalt der
 * {@link ClientException}.
 * </p>
 * <p>
 * Für alle anderen Exceptions liefert der Server 500 Internal Server Error
 * </p>
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
	public static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ServerError> handleClientException(ClientException ex) {
		logger.error("Client exception occured", ex);
		return ResponseEntity.badRequest().body(new ServerError(ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public void handleRemainingExceptions(Throwable ex) {
		logger.error("Unhandled exception occured", ex);
	}

}
