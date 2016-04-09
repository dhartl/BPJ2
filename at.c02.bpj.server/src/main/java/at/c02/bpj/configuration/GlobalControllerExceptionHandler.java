package at.c02.bpj.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import at.c02.bpj.exception.ClientException;
import at.c02.bpj.server.bean.ServerError;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ServerError> handleClientException(ClientException ex) {
		return ResponseEntity.badRequest().body(new ServerError(ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public void handleRemainingExceptions() {

	}

}
